export default [
  {
    path: '/',
    name: 'login-page',
    component: require('views/LoginView')
  },
  {
    path: '/home',
    name: 'home-page',
    component: require('views/HomePageView')
  },
  {
    path: '/gerir-caixa',
    name: 'gerir-caixa',
    component: require('views/GerirCaixaView')
  },
  {
    path: '/gerir-pre-venda',
    name: 'gerir-pre-venda',
    component: require('views/pre-venda/GerirPreVendaView')
  },
  {
    path: '/gerir-produtos',
    name: 'gerir-produtos',
    component: require('views/produto/GerirProdutosView')
  },
  {
    path: '/gerir-clientes',
    name: 'gerir-clientes',
    component: require('views/cliente/GerirClientesView')
  },
  {
    path: '/gerir-acessos',
    name: 'gerir-acessos',
    component: require('views/acessos/GerirAcessosView')
  },
  {
    path: '*',
    redirect: '/'
  }
]
