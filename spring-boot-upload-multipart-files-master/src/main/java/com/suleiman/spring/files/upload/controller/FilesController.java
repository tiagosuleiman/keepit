package com.suleiman.spring.files.upload.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.suleiman.spring.files.upload.message.ResponseMessage;
import com.suleiman.spring.files.upload.model.FileInfo;
import com.suleiman.spring.files.upload.service.FilesStorageService;

@Controller
@CrossOrigin("http://localhost:4200")
public class FilesController {

  @Autowired
  FilesStorageService storageService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      // storageService.save(file);
      InputStream is = new ByteArrayInputStream(file.getBytes());
      String xmlString = FilesController.convertInputStreamToString(is);      
      var xmlSemPrecosMedios = xmlString.replaceAll("<precoMedio>[\\s\\S]*?</precoMedio>","");
      
      // >>>> Requisito ::: Não apresentar Preços Medios das regiões - descomentar pra ver resultado
      //System.err.println(xmlSemPrecosMedios);
      
      Document doc = convertStringToXMLDocument(xmlSemPrecosMedios);
      NodeList list = doc.getElementsByTagName("agente");

      for (int temp = 0; temp < list.getLength(); temp++) {
        Node node = list.item(temp);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          String codigo = element.getElementsByTagName("codigo").item(0).getTextContent();
          
          // >>>> Requisito ::: apresentar somente os códigos dos agentes
          System.out.println("Current Element :" + node.getNodeName());
          System.out.println("Codigo Agente : " + codigo);

        }
      }
     
      // >>>> Requisito ::: Não apresentar Preços Medios das regiões - descomentar pra ver resultado
      //System.out.println(docXmltoString(doc));
      
      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files")
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }

  private static String convertInputStreamToString(InputStream is) throws IOException {

    final int DEFAULT_BUFFER_SIZE = 8192;
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    int length;
    while ((length = is.read(buffer)) != -1) {
      result.write(buffer, 0, length);
    }

    // return result.toString(StandardCharsets.UTF_8.name());
    return result.toString("UTF-8");
  }

  private static Document convertStringToXMLDocument(String xmlString) {
    // Parser that produces DOM object trees from XML content
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    // API to obtain DOM Document instance
    DocumentBuilder builder = null;
    try {
      // Create DocumentBuilder with default configuration
      builder = factory.newDocumentBuilder();

      // Parse the content to Document object
      Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
      return doc;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static String docXmltoString(Document doc) {
    try {
        StringWriter sw = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        transformer.transform(new DOMSource(doc), new StreamResult(sw));
        return sw.toString();
    } catch (Exception ex) {
        throw new RuntimeException("Error converting to String", ex);
    }
}

}
