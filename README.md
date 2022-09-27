# keepit

Video explicativo do desafio e não listado para visualização youtube
link direto:

   https://www.youtube.com/watch?v=npzbOh-vSoY

Clonar:
    
   $ git clone https://github.com/tiagosuleiman/keepit.git
   
Diretorios:
suleiman@tiago:~/dev/code/java/keepit$ ls -a
   
   :: files  
   :: .git  
   :: .gitignore
   :: README.md  
   :: spring-boot-upload-multipart-files-master  
   :: web

Etapas: 

   1 - Entrar na pasta web
      >> ler README.md

   2 - Entrar na pasta spring-boot-upload-multipart-files-master
      >> ler README.md

URL:
    frontend: http://localhost:4200
    backend : http://localhost:8080

PS: 
   @CrossOrigin("http://localhost:4200")
     
###Desafio

	Criar um sistema Web composto de um Front-end SPA (Single Page Application) Angular e um Back-end Java Spring Boot para envio de arquivos XML e posterior processamento deles.

#### Requisitos

 Criar uma interface Web para upload de um ou mais arquivos com extensão .xml.

 Para o desenvolvimento da interface, deve ser utilizado o tema Indigo do Angular Material.

 Durante o envio do(s) arquivo(s) mostrar um loader para informar ao usuário que estão sendo processados, e ao final esse loader deve desaparecer (utilizar componentes do Angular Material).

 Os arquivos contêm uma lista de agentes com código e data em formato ISO, e uma lista com quatro regiões (SE, S, NE, N) com sete valores numéricos de geração, compra e preço médio.

 Todos os arquivos seguem o mesmo formato, como nos exemplos em anexo:

   oexemplo_01.xml
   oexemplo_02.xml
   oexemplo_03.xml


 Não é necessário validar os dados dos arquivos, considere que eles estarão sempre corretos e no formato especificado acima.

 Os arquivos devem ser lidos e enviados um a um, sequencialmente.

 Os arquivos podem conter quantidades grandes de dados, por exemplo, 1.000 agentes (agentes/agente[0..999])

 Os dados de preço médio (/agentes/agente[]/submercado[]/precoMedio) são confidenciais, portanto devem ser removidos ou substituídos por valores em branco antes de serem enviados.

 Ao receber cada arquivo, o back-end deve apenas imprimir na saída padrão (System.out) os códigos de agentes (/agentes/agente[]/codigo) recebidos.
		
Instruções
   1.Criar o sistema utilizando as seguintes tecnologias base:
      Front-end:
                  oAngular 12+
                  oAngular Material 12+
                  oTypescript 4+
                  oRxJS 6+
                  oNodeJS 14+

      Back-end:
                  oSpring Boot 2
                  oMaven 3

2.Disponibilizá-lo em um repositório Git público (exemplo: GitHub, Bitbucket).
	
