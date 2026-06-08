export default {
	baseUrl: 'http://localhost:8080/fleamarket/',
	name: '/fleamarket',
	indexNav: [
		{
			name: '商品信息',
			url: '/index/shangpinxinxi',
		},
		{
			name: '信息资讯',
			url: '/index/news'
		},
		{
			name: '留言反馈',
			url: '/index/messages'
		},
	],
	cateList: [
		{
			name: '信息资讯',
			refTable: 'newstype',
			refColumn: 'typename',
		},
	]
}
