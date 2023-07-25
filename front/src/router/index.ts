import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '/src/views/HomeView.vue'
import WriteView from '/src/views/WriteView.vue'
import ReadView from '/src/views/ReadView.vue'
import EditView from '/src/views/EditView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView,
        },
        {
            path: '/write',
            name: 'write',
            component: WriteView,
        },
        {
            path: '/read/:postId',
            name: 'read',
            component: ReadView,
            props: true,
        },
        {
            path: '/edit/:postId',
            name: 'edit',
            component: EditView,
            props: true,
        },
    ],
});

export default router
