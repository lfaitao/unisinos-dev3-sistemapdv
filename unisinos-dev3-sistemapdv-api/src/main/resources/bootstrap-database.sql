insert into credenciais(id, login,senha) values (1, "admin", "admin");

insert into usuarios(id, nome) value(1, "Talita Audibert");
insert into usuarios(id, nome) value(2, "Eduardo Pereira");
insert into usuarios(id, nome) value(3, "Leonardo Faitão");
insert into usuarios(id, nome) value(4, "Fabio Junqueira");
insert into usuarios(id, nome) value(5, "Josué Silva");

insert into permissoes(id, nome) value(1, "Administrador");
insert into permissoes(id, nome) value(2, "Gerente");
insert into permissoes(id, nome) value(3, "Vendedor");

insert into telas(id, nome, path) value(1, "Home", "/home");
insert into telas(id, nome, path) value(2, "Gerir Acessos", "/gerir-acessos");
insert into telas(id, nome, path) value(3, "Gerir Caixa", "/gerir-caixa");
insert into telas(id, nome, path) value(4, "Gerir Pré-vendas", "/gerir-pre-venda");
insert into telas(id, nome, path) value(5, "Gerir Clientes", "/gerir-clientes");

insert into clientes(id, nome, cpf) values (1, 'Josefina Junqueira', '09876543222');
insert into clientes(id, nome, cpf) values (2, 'Mário Faitão', '12345676543');
insert into clientes(id, nome, cpf) values (3, 'Maria Silva', '09876543456');
insert into clientes(id, nome, cpf) values (4, 'Pedro Pereira', '87650987651');
insert into clientes(id, nome, cpf) values (5, 'Lurdes Audibert', '45671298346');

insert into produtos(id, descricao, estoque, valor) values(1, "Sapato", 100, 200);
insert into produtos(id, descricao, estoque, valor) values(2, "Calça", 200, 250);
insert into produtos(id, descricao, estoque, valor) values(3, "Camisa", 300, 300);
insert into produtos(id, descricao, estoque, valor) values(4, "Meias", 400, 350);
insert into produtos(id, descricao, estoque, valor) values(5, "Saia", 500, 400);
commit;