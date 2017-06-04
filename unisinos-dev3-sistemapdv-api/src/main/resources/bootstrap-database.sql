insert into credenciais(id, login,senha) values (1, "admin", "admin");
insert into usuarios(id, nome) values (1, "teste");
insert into usuario_credencial values (1, 1);

insert into telas(id, nome, path) value(1, "Home", "/home");
insert into telas(id, nome, path) value(2, "Gerir Acessos", "/gerir-acessos");
insert into telas(id, nome, path) value(3, "Gerir Caixa", "/gerir-caixa");
insert into telas(id, nome, path) value(4, "Gerir Pré-vendas", "/gerir-pre-venda");
insert into telas(id, nome, path) value(5, "Gerir Clientes", "/gerir-clientes");

insert into permissoes(id, nome) value(1, "Administrador");
insert into permissoes(id, nome) value(2, "Gerente");
insert into permissoes(id, nome) value(3, "Vendedor");

insert into permissao_tela values (1, 1);
insert into permissao_tela values (1, 2);
insert into permissao_tela values (1, 3);
insert into permissao_tela values (1, 4);
insert into permissao_tela values (1, 5);

insert into usuario_permissao values (1, 1);
insert into usuario_permissao values (1, 2);
insert into usuario_permissao values (1, 3);

insert into usuarios(id, nome) value(1, "Talita Audibert");
insert into usuarios(id, nome) value(2, "Eduardo Pereira");
insert into usuarios(id, nome) value(3, "Leonardo Faitão");
insert into usuarios(id, nome) value(4, "Fabio Junqueira");
insert into usuarios(id, nome) value(5, "Josué Silva");

insert into clientes(id, nome, cpf) values (1, 'Josefina Junqueira', '59623196130');
insert into clientes(id, nome, cpf) values (2, 'Mário Faitão', '85535777676');
insert into clientes(id, nome, cpf) values (3, 'Maria Silva', '06073470908');
insert into clientes(id, nome, cpf) values (4, 'Pedro Pereira', '18873898904');
insert into clientes(id, nome, cpf) values (5, 'Lurdes Audibert', '88247815001');

insert into produtos(id, descricao, estoque, valor) values(1, "Sapato", 100, 200);
insert into produtos(id, descricao, estoque, valor) values(2, "Calça", 200, 250);
insert into produtos(id, descricao, estoque, valor) values(3, "Camisa", 300, 300);
insert into produtos(id, descricao, estoque, valor) values(4, "Meias", 400, 350);
insert into produtos(id, descricao, estoque, valor) values(5, "Saia", 500, 400);

insert into prevenda values(1,1);
insert into prevenda_produto values(1,5,1,1);
insert into prevenda_produto values(2,10,1,2);
commit;