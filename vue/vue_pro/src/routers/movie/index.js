export default {
    path: "/movie",
    component: ()=> import('@/views/movie'),
    children: [
        {
            path: 'city',
            component: ()=> import('@/components/city')
        },
        {
            path: 'commingsoon',
            component: ()=> import('@/components/commingsoon')
        },
        {
            path: 'search',
            component: ()=> import('@/components/search')
        },
        {
            path: '/movie',
            redirect: '/movie/commingsoon'
        }
    ]
}