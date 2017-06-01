export default [
  {
    path: '/',
    name: 'login-page',
    component: require('views/login/LoginView')
  },
  {
    path: '/home',
    name: 'home-page',
    component: require('views/HomePageView')
  },
  {
    path: '/gerir-caixa',
    name: 'gerir-caixa',
    component: require('views/caixa/GerirCaixaView')
  },
  {
    path: '/gerir-venda',
    name: 'gerir-venda',
    component: require('views/GerirVendaView')
  }, 
  {
    path: '/iniciar-venda',
    name: 'iniciar-venda',
    component: require('views/IniciarVendaView')
  },
  {
    path: '/mesclar-pre-venda',
    name: 'mesclar-pre-venda',
    component: require('views/MesclarPreVendaView')
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
    path: '/gerir-davs',
    name: 'gerir-davs',
    component: require('views/dav/GerirDavView')
  },
  {
    path: '*',
    redirect: '/'
  }
]
