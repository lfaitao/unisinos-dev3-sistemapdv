export default [
    {
        path: '/login',
        name: 'login-page',
        component: require('components/LoginView'),
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/register',
        name: 'register-page',
        component: require('components/RegisterView'),
        meta: {
            requiresAuth: false
        }
    },
    {
        path: '/',
        component: require('components/AdminView'),
        children: [
            {
                path: '',
                component: require('components/DashboardView'),
                name: 'Dashboard',
            },
            {
                path: 'documents/pending',
                component: require('components/DocumentsListPendingView'),
                name: 'docs-pending',
            },
            {
                path: 'documents/received',
                component: require('components/DocumentsListReceivedView'),
                name: 'docs-received',
            },
            {
                path: 'documents/sent',
                component: require('components/DocumentsListSentView'),
                name: 'docs-sent',
            },
            {
                path: 'documents/error',
                component: require('components/DocumentsListErrorView'),
                name: 'docs-error',
            },
            {
                path: 'configuration/directories',
                component: require('components/UserConfigurationView'),
                name: 'user-configuration-page'
            },
            {
                path: 'configuration/credentials',
                component: require('components/UserCredentialsView'),
                name: 'user-credentials-page'
            }
        ],
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '*',
        redirect: '/'
    }
]


