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
    path: '*',
    redirect: '/'
  }
]
