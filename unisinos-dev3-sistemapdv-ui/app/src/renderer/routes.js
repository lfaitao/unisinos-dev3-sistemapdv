
function acesso(to, from, next){

  var permissoes = JSON.parse(window.localStorage.getItem('teste'));

  if(permissoes){
    var i; var j; var hasPermission = false;
    
    for(i in permissoes){

       for(j in permissoes[i].telas){
         
         if(to.path === permissoes[i].telas[j].path){
          hasPermission = true;
          break;
         }

       }

       if(hasPermission)
        break;
     }
   }
   
   if(hasPermission){ next(); }
 
}

export default [
  {
    path: '/',
    name: 'login-page',
    component: require('views/LoginView')
  },
  {
    path: '/home',
    name: 'home-page',
    component: require('views/HomePageView'),
    beforeEnter: acesso
  },
  {
    path: '/gerir-caixa',
    name: 'gerir-caixa',
    component: require('views/GerirCaixaView'),
    beforeEnter: acesso
  },
  {
    path: '/gerir-venda',
    name: 'gerir-venda',
    component: require('views/GerirVendaView'),
    beforeEnter: acesso
  }, 
  {
    path: '/iniciar-venda',
    name: 'iniciar-venda',
    component: require('views/IniciarVendaView'),
    beforeEnter: acesso
  },
  {
    path: '/mesclar-pre-venda',
    name: 'mesclar-pre-venda',
    component: require('views/MesclarPreVendaView'),
    beforeEnter: acesso
  },
  {
    path: '/gerir-pre-venda',
    name: 'gerir-pre-venda',
    component: require('views/pre-venda/GerirPreVendaView'),
    beforeEnter: acesso
  },
  {
    path: '/gerir-produtos',
    name: 'gerir-produtos',
    component: require('views/produto/GerirProdutosView'),
    beforeEnter: acesso
  },
  {
    path: '/gerir-clientes',
    name: 'gerir-clientes',
    component: require('views/cliente/GerirClientesView'),
    beforeEnter: acesso
  },
  {
    path: '/gerir-acessos',
    name: 'gerir-acessos',
    component: require('views/acessos/GerirAcessosView'),
    beforeEnter: acesso
  },
  {
    path: '/gerir-davs',
    name: 'gerir-davs',
    component: require('views/dav/GerirDavView'),
    beforeEnter: acesso
  },
  {
    path: '*',
    redirect: '/'
  }
]
