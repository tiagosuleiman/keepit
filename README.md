# keepit

Video explicativo do desafio e não listado para visualização youtube
link direto:

   https://www.youtube.com/watch?v=npzbOh-vSoY

Clonar:
```shell script    
$ git clone https://github.com/tiagosuleiman/keepit.git
```  
  
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
   
Versões Ambiente:

     $ java -version
     
	openjdk version "11.0.16" 2022-07-19
	OpenJDK Runtime Environment (build 11.0.16+8-post-Ubuntu-0ubuntu120.04)
	OpenJDK 64-Bit Server VM (build 11.0.16+8-post-Ubuntu-0ubuntu120.04, mixed mode, sharing)

     $ node -v
	
	v16.14.2

     $ npm -v
	
	8.19.2
	
     $ mvn -v
     
	Apache Maven 3.6.3
	Maven home: /usr/share/maven
	Java version: 11.0.16, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
	Default locale: pt_BR, platform encoding: UTF-8
	OS name: "linux", version: "5.4.0-110-generic", arch: "amd64", family: "unix"
	
     $ cat /etc/os-release 
     
	NAME="Linux Mint"
	VERSION="20.3 (Una)"
	ID=linuxmint
	ID_LIKE=ubuntu
	PRETTY_NAME="Linux Mint 20.3"
	VERSION_ID="20.3"
	HOME_URL="https://www.linuxmint.com/"
	SUPPORT_URL="https://forums.linuxmint.com/"
	BUG_REPORT_URL="http://linuxmint-troubleshooting-guide.readthedocs.io/en/latest/"
	PRIVACY_POLICY_URL="https://www.linuxmint.com/"
	VERSION_CODENAME=una
	UBUNTU_CODENAME=focal

        
### Desafio:

	Criar um sistema Web composto de um Front-end SPA (Single Page Application) Angular e um Back-end Java Spring Boot para envio de arquivos XML e posterior processamento deles.

#### Requisitos:

 Criar uma interface Web para upload de um ou mais arquivos com extensão .xml.

 Para o desenvolvimento da interface, deve ser utilizado o tema Indigo do Angular Material.

 Durante o envio do(s) arquivo(s) mostrar um loader para informar ao usuário que estão sendo processados, e ao final esse loader deve desaparecer (utilizar componentes do Angular Material).

 Os arquivos contêm uma lista de agentes com código e data em formato ISO, e uma lista com quatro regiões (SE, S, NE, N) com sete valores numéricos de geração, compra e preço médio.

 Todos os arquivos seguem o mesmo formato, como nos exemplos em anexo:

  	o exemplo_01.xml
   	o exemplo_02.xml
   	o exemplo_03.xml

 Não é necessário validar os dados dos arquivos, considere que eles estarão sempre corretos e no formato especificado acima.

 Os arquivos devem ser lidos e enviados um a um, sequencialmente.

 Os arquivos podem conter quantidades grandes de dados, por exemplo, 1.000 agentes (agentes/agente[0..999])

 Os dados de preço médio (/agentes/agente[]/submercado[]/precoMedio) são confidenciais, portanto devem ser removidos ou substituídos por valores em branco antes de serem enviados.

 Ao receber cada arquivo, o back-end deve apenas imprimir na saída padrão (System.out) os códigos de agentes (/agentes/agente[]/codigo) recebidos.
		
#### Instruções:

   1.Criar o sistema utilizando as seguintes tecnologias base:
     
      Front-end:
                  o Angular 12+
                  o Angular Material 12+
                  o Typescript 4+
                  o RxJS 6+
                  o NodeJS 14+
		  
      Back-end:
                  o Spring Boot 2
                  o Maven 3

2.Disponibilizá-lo em um repositório Git público (exemplo: GitHub, Bitbucket).
	
