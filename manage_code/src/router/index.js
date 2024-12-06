	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import fenpeibangongshi from '@/views/fenpeibangongshi/list'
	import bangongshi from '@/views/bangongshi/list'
	import jiaoshixinxi from '@/views/jiaoshixinxi/list'
	import fenpeisushe from '@/views/fenpeisushe/list'
	import sushexinxi from '@/views/sushexinxi/list'
	import xueshengxinxi from '@/views/xueshengxinxi/list'
	import xueshengxinxiRegister from '@/views/xueshengxinxi/register'
	import xueshengxinxiCenter from '@/views/xueshengxinxi/center'
	import jiaoshixinxiRegister from '@/views/jiaoshixinxi/register'
	import jiaoshixinxiCenter from '@/views/jiaoshixinxi/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/xueshengxinxiCenter',
			name: '学生信息个人中心',
			component: xueshengxinxiCenter
		}
		,{
			path: '/jiaoshixinxiCenter',
			name: '教师信息个人中心',
			component: jiaoshixinxiCenter
		}
		,{
			path: '/fenpeibangongshi',
			name: '分配办公室',
			component: fenpeibangongshi
		}
		,{
			path: '/bangongshi',
			name: '办公室',
			component: bangongshi
		}
		,{
			path: '/jiaoshixinxi',
			name: '教师信息',
			component: jiaoshixinxi
		}
		,{
			path: '/fenpeisushe',
			name: '分配宿舍',
			component: fenpeisushe
		}
		,{
			path: '/sushexinxi',
			name: '宿舍信息',
			component: sushexinxi
		}
		,{
			path: '/xueshengxinxi',
			name: '学生信息',
			component: xueshengxinxi
		}
		]
	},
	{
		path: '/xueshengxinxiRegister',
		name: '学生信息注册',
		component: xueshengxinxiRegister
	},
	{
		path: '/jiaoshixinxiRegister',
		name: '教师信息注册',
		component: jiaoshixinxiRegister
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
