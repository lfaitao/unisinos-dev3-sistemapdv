export default [
  {
    path: '/',
    name: 'home-page',
    component: require('components/HomePageView')
  },
  {
    path: '/gerir-caixa',
    name: 'gerir-caixa',
    component: require('components/GerirCaixaView')
  },
  {
    path: '*',
    redirect: '/'
  }
]
