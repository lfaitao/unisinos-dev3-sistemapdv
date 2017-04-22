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
    path: '/gerir-acessos',
    name: 'gerir-acessos',
    component: require('views/acessos/GerirAcessosView')
  },
  {
    path: '*',
    redirect: '/'
  }
]
