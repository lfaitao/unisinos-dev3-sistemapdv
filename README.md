# Sistema PDV #
Alunos:
- Eduardo Pereira
- Fábio Blumm
- Josué Silva
- Leonardo Faitão
- Talita Audibert

# Instruções de Execução #

# Iniciando a aplicação em DIST

1 - Instalar MySQL Server e deixar rodando em background;
2 - Iniciar o executável que está no caminho:
	"unisinos-dev3-sistemapdv-dist/linux-unpacked/sistemapdv"
	Obs.: Só geramos o executável para o Linux;
3 - O programa iniciará com uma Splash Screen de loading. Quando ela fechar e a tela de login aparecer, isso significa que o Hibernate criou o banco e todas as tabelas no banco devidamente;
4 - Para começar utilizando o programa, é necessário entrar no mysql server e rodar o boostrap do banco com o sql que está no caminho:
	"unisinos-dev3-sistemapdv-bd/bootstrap-database.sql";
5 - Agora será possível logar no programa com uma das credenciais abaixo e testá-lo:
	Login: admin - Senha: admin
	Login: gerente - Senha: gerente
	Login: vendedor - Senha: vendedor

# Iniciando a aplicação em DEV

1 - Instalar MySQL Server e deixar rodando em background;
2 - Instalar o jdk e o gradle localmente;
3 - Navegar até "unisinos-dev3-sistemapdv-api" e rodar o comando "gradle bootRun";
	3.1 - A aplicação ficara rodando em http://localhost:8080/sistemapdv/api
4 - Navegar até "unisinos-dev3-sistemapdv-ui" e rodar o comando "npm install";
5 - Para iniciar o frontend, rodar "npm run dev"
	5.1 - Se houver algum erro, talvez seja necessário rodar "npm run build" e então "npm run dev" novamente.
6 - O programa iniciará com uma Splash Screen de loading. Essa splashscreen só é necessária em dist, pois é necessário esperar que o backend esteja de pé. Como você rodou o backend no terminal, já vai ter visto quando ele terminou de executar. Mesmo assim terá que esperar a splash fechar e abrir a tela de login.
7 - Para começar utilizando o programa, é necessário entrar no mysql server e rodar o boostrap do banco com o sql que está no caminho:
	"unisinos-dev3-sistemapdv-bd/bootstrap-database.sql"
8 - Agora será possível logar no programa com uma das credenciais abaixo e testá-lo:
	Login: admin - Senha: admin
	Login: gerente - Senha: gerente
	Login: vendedor - Senha: vendedor

# Qualquer problema em executar, por favor contatar o grupo!