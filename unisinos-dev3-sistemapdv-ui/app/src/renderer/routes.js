export default [
  {
    path: '/',
    name: 'home-page',
    component: require('views/HomePageView')
  },
  {
    path: '/gerir-caixa',
    name: 'gerir-caixa',
    component: require('views/GerirCaixaView')
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
    path: '*',
    redirect: '/'
  }
]
