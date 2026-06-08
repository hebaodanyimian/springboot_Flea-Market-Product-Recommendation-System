const base = {
    get() {
        return {
            url : "http://localhost:8080/fleamarket/",
            name: "fleamarket",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/fleamarket/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于协同过滤算法的跳蚤市场商品推荐系统"
        } 
    }
}
export default base
