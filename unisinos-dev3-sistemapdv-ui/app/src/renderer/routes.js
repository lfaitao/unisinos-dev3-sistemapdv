export default [
  {
    path: '/',
    name: 'home-page',
    component: require('components/HomePageView')
  },
  {
    path: '*',
    redirect: '/'
  }
]
