<template>
	<div>
	<!-- shangjiazhanghao -->
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="''">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index" :to="'/index/shangpinxinxi?centerType=' + (centerType?'1':'0')"><a>{{item.name}}</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item3"><a href="javascript:void(0);">详情</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="detail-preview">
			<div class="attr">
				<div class="info">
					<div class="title-item">
						<div class="detail-title">
							{{detail.shangpinmingcheng}}
						</div>
						<div class="colectBtn" @click="storeup(1)" v-show="!isStoreup">
							<i class="icon iconfont icon-shoucang12"></i>
							<span class="text">收藏({{detail.storeupnum}})</span>
						</div>
						<div class="colectBtnActive" @click="storeup(-1)" v-show="isStoreup">
							<i class="icon iconfont icon-shoucang12"></i>
							<span class="text">已收藏({{detail.storeupnum}})</span>
						</div>
					</div>
					<div class="item" v-if="detail.price">
						<div class="lable">价格</div>
						<div class="text price bold"><span style="font-size: 12px">￥</span>{{detail.price}}</div>
					</div>
					<div class="item" v-if="detail.jf">
						<div class="lable">积分</div>
						<div class="text price bold">{{detail.jf}}</div>
					</div>
					<div class="item">
						<div class="lable">单限</div>
						<div class="text">{{detail.onelimittimes}}</div>
					</div>
					<div class="item">
						<div class="lable">库存</div>
						<div class="text">{{detail.alllimittimes}}</div>
					</div>
					<div class="item">
						<div class="lable">商品分类</div>
						<div class="text "  >{{detail.shangpinfenlei}}</div>
					</div>
					<div class="item">
						<div class="lable">商品规格</div>
						<div class="text "  >{{detail.shangpinguige}}</div>
					</div>
					<div class="item">
						<div class="lable">商品品牌</div>
						<div class="text "  >{{detail.shangpinpinpai}}</div>
					</div>
					<div class="item">
						<div class="lable">上架时间</div>
						<div class="text "  >{{detail.shangjiashijian}}</div>
					</div>
					<div class="item">
						<div class="lable">商家账号</div>
						<div class="text  link" @click="merchantClick('shangjiazhanghao')" >{{detail.shangjiazhanghao}}</div>
					</div>
					<div class="item">
						<div class="lable">商家名称</div>
						<div class="text "  >{{detail.shangjiamingcheng}}</div>
					</div>
					<div class="item">
						<div class="lable">点击次数</div>
						<div class="text "  >{{detail.clicknum}}</div>
					</div>
					<div class="item" v-if="centerType">
						<div class="lable">审核状态</div>
						<div class="text">{{detail.sfsh=='是'?'通过':(detail.sfsh=='否'?'不通过':'待审核')}}</div>
					</div>
					<div class="item" v-if="centerType">
						<div class="lable">审核回复</div>
						<div class="text">{{detail.shhf}}</div>
					</div>
					<div class="btn_box">
						<el-input-number v-if="detail.alllimittimes" :min=1 v-model="buynumber"></el-input-number>
						<el-button class="addCartBtn" v-if="detail.alllimittimes" type="warning" size="small" @click="addCart">添加到购物车</el-button>
						<el-button class="buyBtn" v-if="detail.alllimittimes" type="warning" size="small" @click="buyNow">立即购买</el-button>

					</div>
					<div class="btn_box">
						<el-button class="editBtn" v-if="btnAuth('shangpinxinxi','修改')&&detail.sfsh!='是'&&detail.sfsh!='否' " @click="editClick">修改</el-button>
						<el-button class="delBtn" v-if="btnAuth('shangpinxinxi','删除')" @click="delClick">删除</el-button>
						<el-button class="chatBtn" v-if="btnAuth('shangpinxinxi','私聊')&&detail.id!=mid" @click="chatClick">联系TA</el-button>
						<el-button class="auditBtn" v-if="btnAuth('shangpinxinxi','审核') && (detail.sfsh=='待审核'||!detail.sfsh)" @click="onSH" type="warning">审核</el-button>
					</div>
				</div>
			</div>
		
			<div class="swiper3" v-if="detailBanner.length">
				<div class="big">
					<img id="big" :src="swiperBigUrl" class="image">
				</div>
				<div class="samll">
					<div class="swiper3-small-item" v-for="item in detailBanner" :key="item.id">
						<img v-if="item.substr(0,4)=='http'" :src="item" @click="swiperClick3(item)" class="image">
						<img v-else :src="baseUrl + item" @click="swiperClick3(baseUrl + item)" class="image">
					</div>
				</div>
			</div>

			<div class="zancai">
				<div v-if="!isThumbsupnum && !isCrazilynum" class="zan" @click="thumbsupOrCrazily(21)">
					<i class="icon iconfont icon-zan10"></i>
					<span class="text">赞一下({{detail.thumbsupnum}})</span>
				</div>
				<div v-if="!isThumbsupnum && !isCrazilynum" class="cai" @click="thumbsupOrCrazily(22)">
					<i class="icon iconfont icon-cai15"></i>
					<span class="text">踩一下({{detail.crazilynum}})</span>
				</div>
				<div v-if="isThumbsupnum" class="zanActive" @click="cancelThumbsupOrCrazily(21)">
					<i class="icon iconfont icon-zan10"></i>
					<span class="text">已赞({{detail.thumbsupnum}})</span>
				</div>
				<div v-if="isCrazilynum" class="caiActive" @click="cancelThumbsupOrCrazily(22)">
					<i  class="icon iconfont icon-cai15"></i>
					<span class="text">已踩({{detail.crazilynum}})</span>
				</div>
			</div>

		

			<el-tabs class="detail-tabs" v-model="activeName" type="border-card" v-if="tabsNum>0" >
				<el-tab-pane label="商品介绍" name="first">
					<div class="ql-snow ql-editor" v-html="detail.shangpinjieshao"></div>
				</el-tab-pane>
				<el-tab-pane label="评论" name="second">
					<el-form class="add commentForm" :model="form" :rules="rules" ref="form">
						<el-form-item class="item" label="评论" prop="content">
							<editor
								myQuillEditor="content"
								v-model="form.content" 
								class="editor" 
								action="file/upload">
							</editor>
						</el-form-item>
						<el-form-item class="commentBtn">
							<el-button class="submitBtn" type="primary" @click="submitForm('form')">立即提交</el-button>
							<el-button class="resetBtn" @click="resetForm('form')">重置</el-button>
						</el-form-item>
					</el-form>
				
					<div v-if="infoList.length" class="comment-list">
						<div class="comment-item" v-for="item in infoList" :key="item.id" @mouseenter="discussEnter(item.id)"
							@mouseleave="discussLeave">
							<div class="istop" v-if="item.istop">
								<span class="icon iconfont icon-jiantou24"></span>
							</div>
							<div class="user">
								<el-image v-if="item.avatarurl" :size="50" :src="baseUrl + item.avatarurl"></el-image>
								<el-image v-if="!item.avatarurl" :size="50" :src="require('@/assets/touxiang.png')"></el-image>
								<div class="name">{{item.nickname}}</div>
							</div>
							<div class="comment-content-box">
								<div class="ql-snow ql-editor" v-html="item.content"></div>
								<div class="comment-time">{{item.addtime}}</div>
								<div class="zancai-box">
									<div v-if="!comcaiChange(item)" class="zan-item" :class="comzanChange(item)?'active':''" @click="comzanClick(item)">
										<span class="icon iconfont" :class="comzanChange(item)?'icon-zan11':'icon-zan07'"></span>
										<span class="label">{{comzanChange(item)?'已赞':'赞'}}</span>
										<span class="num">({{item.thumbsupnum}})</span>
									</div>
									<div v-if="!comzanChange(item)" class="cai-item" :class="comcaiChange(item)?'active':''" @click="comcaiClick(item)">
										<span class="icon iconfont" :class="comcaiChange(item)?'icon-cai16':'icon-cai01'"></span>
										<span class="label">{{comcaiChange(item)?'已踩':'踩'}}</span>
										<span class="num">({{item.crazilynum}})</span>
									</div>
								</div>
								<div class="comment-btn">
									<!-- <el-button :style='{"border":"0","cursor":"pointer","padding":"0 20px","margin":"0 10px","color":"#fff","borderRadius":"0px","background":"rgb(213, 103, 147)","width":"auto","lineHeight":"32px","fontSize":"14px","minWidth":"90px","height":"32px"}'>回复</el-button> -->
									<el-button class="delBtn" v-if="showIndex==item.id&&userid==item.userid" @click="discussDel(item.id)">删除</el-button>
								</div>
							</div>
							<div class="comment-content-box" v-if="item.reply">
								回复：<span class="ql-snow ql-editor" v-html="item.reply"></span>
							</div>
						</div>
					</div>
				
					<el-pagination
						background
						id="pagination" class="pagination"
						:page-size="pageSize"
						prev-text="上一页"
						next-text="下一页"
						:hide-on-single-page="false"
						:layout='["total","prev","pager","next","sizes","jumper"].join()'
						:total="total"
						@current-change="curChange"
						@prev-click="prevClick"
						@next-click="nextClick"
						@size-change="sizeChange"
						></el-pagination>
				</el-tab-pane>
			</el-tabs>
			<el-dialog title="审核" :visible.sync="sfshVisiable" width="50%">
				<el-form ref="shForm" :model="shForm" label-width="80px">
					<el-form-item label="审核状态">
						<el-select v-model="shForm.sfsh" placeholder="审核状态">
							<el-option label="通过" value="是"></el-option>
							<el-option label="不通过" value="否"></el-option>
							<el-option label="待审核" value="待审核"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="内容">
						<el-input type="textarea" :rows="8" v-model="shForm.shhf"></el-input>
					</el-form-item>
				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button @click="sfshVisiable = false">取 消</el-button>
					<el-button type="primary" @click="shHandler">确 定</el-button>
				</div>
			</el-dialog>

			<div class="idea1"></div>
			<div class="idea2"></div>
		</div>
		<div class="share_view">
		</div>
		<el-dialog :visible.sync="chatVisible" @close="clearChat" :title="fname">
			<div class="chat-content" id="chat-content">
				<div v-bind:key="item.id" v-for="item in chatList">
					<div v-if="item.addtime" class="addtime">{{timeFormat(item.addtime)}}</div>
					<div v-if="item.uid==mid" class="right-content">
						<el-alert v-if="item.format==1" class="text-content" :title="item.content" :closable="false"
							type="warning"></el-alert>
						<el-image v-else fit="cover" :src="item.content?baseUrl + item.content:''" style="width: 100px;height: 100px;" :preview-src-list="[item.content?baseUrl + item.content:'']"></el-image>
						<img :src="mpic?baseUrl + mpic:require('@/assets/avator.png')" alt="" style="width: 30px;border-radius: 50%;height: 30px;margin: 0 0 0 10px;" />
					</div>
					<div v-else class="left-content">
						<img :src="fpic?baseUrl + fpic:require('@/assets/avator.png')" alt="" style="width: 30px;border-radius: 50%;height: 30px;margin: 0 10px 0 0;" />
						<el-alert v-if="item.format==1" class="text-content" :title="item.content" :closable="false"
							type="success"></el-alert>
						<el-image v-else fit="cover" :src="item.content?baseUrl + item.content:''" style="width: 100px;height: 100px;" :preview-src-list="[item.content?baseUrl + item.content:'']"></el-image>
						
					</div>
					<div class="clear-float"></div>
				</div>
			</div>
			<div slot="footer" class="dialog-footer">
				<el-input size="small" @keydown.enter.native="addChat(null)" v-model="chatForm.content" placeholder="请输入内容" style="width: calc(100% - 240px);float: left;">
				</el-input>
				<el-button size="small" :disabled="chatForm.content?false:true" type="primary" @click="addChat(null)">发送</el-button>
				<el-upload style="display: inline-block;margin: 0 6px;" class="upload-demo" :action="uploadUrl" :on-success="uploadSuccess"
					:show-file-list="false">
					<el-button size="small" type="success">上传图片</el-button>
				</el-upload>
				<el-button v-if="showSaveType" size="small" type="primary" @click="saveGood">详情图发送</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	import axios from 'axios'
	import Swiper from "swiper";
	import timeMethod from '@/common/timeMethod'
	import {
		WebsocketMixin
	} from '@/mixins/WebsocketMixin'
	export default {
		mixins: [WebsocketMixin],
		//数据集合
		data() {
			return {
				tablename: 'shangpinxinxi',
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '商品信息'
					}
				],
				title: '',
				detailBanner: [],
				userid: Number(localStorage.getItem('frontUserid')),
				id: 0,
				detail: {},
				tabsNum: 2,
				activeName: 'second',
				form: {
					content: '',
					userid: Number(localStorage.getItem('frontUserid')),
					nickname: localStorage.getItem('username'),
					avatarurl: '',
				},
				showIndex: -1,
				infoList: [],
				rules: {
					content: [
						{ required: true, message: '请输入内容', trigger: 'blur' }
					],
				},
				total: 1,
				pageSize: 10,
				totalPage: 1,
				storeupParams: {
					name: '',
					picture: '',
					refid: 0,
					tablename: 'shangpinxinxi',
					userid: Number(localStorage.getItem('frontUserid'))
				},
				isStoreup: false,
				storeupInfo: {},
				isCrazilynum: false,
				isThumbsupnum: false,
				thumbsupOrCrazilyInfo: {},
				buynumber: 1,
				cart: {
					buynumber: 0,
					discountprice: 0,
					goodid: 0,
					goodname: '',
					picture: '',
					price: 0,
					userid: Number(localStorage.getItem('frontUserid'))
				},
				isInCart: false,
				sfshVisiable: false,
				shForm: {
					sfsh: '是',
					shhf: ''
				},
				centerType: false,
				storeupType: false,
				shareUrl: location.href,
				swiperBigUrl: null,
				mid: Number(localStorage.getItem('frontUserid')),
				chatVisible: false,
				chatList: [],
				chatForm: {
					content: ''
				},
				uploadUrl: this.$config.baseUrl + 'file/upload',
				fpic: '',
				fname: '',
				fid: '',
				mpic: localStorage.getItem('frontHeadportrait'),
				showSaveType: true,
			}
		},
		created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0) {
				this.centerType = true
			}
			if(this.$route.query.storeupType&&this.$route.query.storeupType!=0) {
				this.storeupType = true
			}
			
			this.init();
		},
		mounted() {
		},
		//方法集合
		methods: {
			swiperClick3(src) {
				this.swiperBigUrl = src
			},
			init() {
				this.id = this.$route.query.id
				this.baseUrl = this.$config.baseUrl;
				this.$http.get(this.tablename + '/detail/'  + this.id, {}).then(res => {
					if (res.data.code == 0) {
						this.detail = res.data.data;
						this.title = this.detail.shangpinmingcheng;
						if(this.detail.shangpintupian) {
							this.detailBanner = this.detail.shangpintupian.split(",w").length>1?[this.detail.shangpintupian]:this.detail.shangpintupian.split(',');
						}
						if(res.data.data.touxiang){
							this.fpic = res.data.data.touxiang.split(',')[0]
						}else if(res.data.data.headportrait){
							this.fpic = res.data.data.headportrait.split(',')[0]
						}
						this.$forceUpdate();
						this.getDiscussList(1);
						if(localStorage.getItem('frontToken')){
							this.getStoreupStatus();
							this.getThumbsupOrCrazilyStatus();
							this.getCartList();
						}

					}
					if (this.detailBanner.length) {
						if (this.detailBanner[0].substr(0,4)=='http') {
							this.swiperBigUrl = this.detailBanner[0]
						} else {
							this.swiperBigUrl = this.baseUrl + this.detailBanner[0]
						}
					}
				});
			},
			merchantClick(name){
				this.$http.get('shangjia/query',{params: {
					shangjiazhanghao: this.detail.shangjiazhanghao
				}}).then(rs=>{
					let params = {
						id: rs.data.data.id
					}
					if(this.centerType){
						params.centerType = 1
					}
					this.$router.push({path: '/index/shangjiaDetail', query: params});
				})
			},
			onSH() {
				this.sfshVisiable = true;
			},
			shHandler(){
				this.$confirm(`确定操作?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(() => {
					this.detail.sfsh = this.shForm.sfsh;
					this.detail.shhf = this.shForm.shhf;
					this.$http.post(`${this.tablename}/update`, this.detail).then(res => {
						if (res.data.code == 0) {
							this.sfshVisiable = false;
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
							});
						}
					});
				});
			},
			storeup(type) {
				if (type == 1 && !this.isStoreup) {
					this.storeupParams.name = this.title;
					this.storeupParams.picture = this.detailBanner[0];
					this.storeupParams.refid = this.detail.id;
					this.storeupParams.type = String(type);
					this.$http.post('storeup/add', this.storeupParams).then(res => {
						if (res.data.code == 0) {
							this.isStoreup = true;
							this.detail.storeupnum++
							this.$http.post('shangpinxinxi/update', this.detail).then(res => {});
							this.$message({
								type: 'success',
								message: '收藏成功!',
								duration: 1500,
							});
						}
					});
				}
				if (type == -1 && this.isStoreup) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: '1', refid: this.detail.id, tablename: 'shangpinxinxi', userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isStoreup = true;
							this.storeupInfo = res.data.data.list[0];
							let delIds = new Array();
							delIds.push(this.storeupInfo.id);
							this.$http.post('storeup/delete', delIds).then(res => {
								if (res.data.code == 0) {
									this.isStoreup = false;
									this.detail.storeupnum--
									this.$http.post('shangpinxinxi/update', this.detail).then(res => {});
									this.$message({
										type: 'success',
										message: '取消成功!',
										duration: 1500,
									});
								}
							});
						}
					});
				}
			},
			getStoreupStatus(){
				if(localStorage.getItem("frontToken")) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: '1', refid: this.detail.id, tablename: 'shangpinxinxi', userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isStoreup = true;
							this.storeupInfo = res.data.data.list[0];
						}
					});
				}
			},
			thumbsupOrCrazily(type) {
				this.storeupParams.name = this.title;
				this.storeupParams.picture = this.detailBanner[0];
				this.storeupParams.refid = this.detail.id;
				this.storeupParams.type = String(type);
				this.$http.post('storeup/add', this.storeupParams).then(res => {
					if (res.data.code == 0) {
						this.getThumbsupOrCrazilyStatus();
						this.$message({
							type: 'success',
							message: '操作成功!',
							duration: 1500,
						});
					}
				});

				if (type == 21) this.detail.thumbsupnum = Number(this.detail.thumbsupnum) + 1;
				if (type == 22) this.detail.crazilynum = Number(this.detail.crazilynum) + 1;
				this.$http.post('shangpinxinxi/update', this.detail).then(res => {});
			},
			cancelThumbsupOrCrazily(type) {
				let delIds = new Array();
				delIds.push(this.thumbsupOrCrazilyInfo.id);
				this.$http.post('storeup/delete', delIds).then(res => {
					if (res.data.code == 0) {
						this.isThumbsupnum = false;
						this.isCrazilynum = false;
						this.$message({
							type: 'success',
							message: '取消成功!',
							duration: 1500,
						});
					}
				});
				if (type == 21) this.detail.thumbsupnum -= 1;
				if (type == 22) this.detail.crazilynum -= 1;
				this.$http.post('shangpinxinxi/update', this.detail).then(res => {});
			},
			getThumbsupOrCrazilyStatus() {
				if(localStorage.getItem("frontToken")) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: '21', refid: this.detail.id, tablename: 'shangpinxinxi', userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isThumbsupnum = true;
							this.thumbsupOrCrazilyInfo = res.data.data.list[0];
						}
					});
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: '22', refid: this.detail.id, tablename: 'shangpinxinxi', userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isCrazilynum = true;
							this.thumbsupOrCrazilyInfo = res.data.data.list[0];
						}
					});
				}
			},
			curChange(page) {
				this.getDiscussList(page);
			},
			prevClick(page) {
				this.getDiscussList(page);
			},
			nextClick(page) {
				this.getDiscussList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getDiscussList(1);
			},
			// 返回按钮
			backClick(){
				if(this.storeupType){
					history.back()
				}else{
					let params = {}
					if(this.centerType){
						params.centerType = 1
					}
					this.$router.push({path: '/index/shangpinxinxi', query: params});
				}
			},
			// 下载
			download(file ){
				if(!file) {
					this.$message({
						type: 'error',
						message: '文件不存在',
						duration: 1500,
					});
					return;
				}
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.baseUrl + '/file/download?fileName=' + arr, {
					headers: {
						token: localStorage.getItem("frontToken")
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$config.name).length>1 ? location.href.split(this.$config.name)[0] :'') + this.$config.name + '/file/download?fileName=' + arr, {
						headers: {
							token: localStorage.getItem("frontToken")
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},
			getDiscussList(page) {
				this.$http.get('discussshangpinxinxi/list', {params: {page, limit: this.pageSize, refid: this.detail.id,sort: 'istop',order: 'desc'}}).then(res => {
					if (res.data.code == 0) {
						this.infoList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
					}
				});
			},
			comzanChange(row){
				if(row.tuserids){
					let arr = row.tuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							return true
						}
					}
				}
				return false
			},
			comzanClick(row){
				if(!this.userid){
					return false
				}
				if(!this.comzanChange(row)){
					row.thumbsupnum++
					if(row.tuserids){
						row.tuserids = row.tuserids + ',' + this.userid
					}else {
						row.tuserids = String(this.userid)
					}
					this.$http.post('discussshangpinxinxi/update',row).then(rs=>{
						this.$message.success('点赞成功')
					})
				}else {
					row.thumbsupnum--
					let arr = row.tuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							arr.splice(x,1)
						}
					}
					row.tuserids = arr.join(',')
					this.$http.post('discussshangpinxinxi/update',row).then(rs=>{
						this.$message.success('取消成功')
					})
				}
			},
			comcaiChange(row){
				if(row.cuserids){
					let arr = row.cuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							return true
						}
					}
				}
				return false
			},
			comcaiClick(row){
				if(!this.userid){
					return false
				}
				if(!this.comcaiChange(row)){
					row.crazilynum++
					if(row.cuserids){
						row.cuserids = row.cuserids + ',' + this.userid
					}else {
						row.cuserids = String(this.userid)
					}
					this.$http.post('discussshangpinxinxi/update',row).then(rs=>{
						this.$message.success('点踩成功')
					})
				}else {
					row.crazilynum--
					let arr = row.cuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							arr.splice(x,1)
						}
					}
					row.cuserids = arr.join(',')
					this.$http.post('discussshangpinxinxi/update',row).then(rs=>{
						this.$message.success('取消成功')
					})
				}
			},
			discussEnter(index){
				this.showIndex = index
			},
			discussLeave(){
				this.showIndex = -1
			},
			discussDel(id){
				this.$confirm('是否删除此评论？').then(_ => {
					this.$http.post('discussshangpinxinxi/delete',[id]).then(res=>{
						if(res.data&&res.data.code==0){
							this.addDiscussNum(1)
							this.$message({
								type: 'success',
								message: '删除成功!',
								duration: 1500,
								onClose: () => {
									this.getDiscussList(1);
								}
							});
						}
					})
				}).catch(_ => {});
			},
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$http.get('orders/list', {params: {page: 1, limit: 1, status: '已完成', goodid: this.detail.id, userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
							if (res.data.code == 0 && res.data.data.list.length == 0) {
								this.$message({
									type: 'error',
									message: '请完成订单后再评论!',
									duration: 1500
								});
								return false
							} else {
								this.form.refid = this.detail.id;
								this.form.avatarurl = localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'';
								this.$http.post('discussshangpinxinxi/add', this.form).then(rs2 => {
									if (rs2.data.code == 0) {
										this.addDiscussNum(2)
										this.form.content = '';
										this.getDiscussList(1);
										this.$message({
											type: 'success',
											message: '评论成功!',
											duration: 1500,
										});
									}
								});
							}
						});
					} else {
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			addDiscussNum(type){
				if(type==2){
					this.detail.discussnum++
				}else if(type==1){
					if(this.detail.discussnum!=0){
						this.detail.discussnum--
					}else{
						this.detail.discussnum = 0
					}
				}
				this.$http.post('shangpinxinxi/update',this.detail).then(res=>{})
			},
			getCartList() {
				this.$http.get('cart/list', {params: {userid: Number(localStorage.getItem('frontUserid')), tablename: 'shangpinxinxi', goodid: this.detail.id}}).then(res => {
					if (res.data.code == 0) {
						if (res.data.data.list.length > 0) {
							this.isInCart = true;
						} else {
							this.isInCart = false;
						}
					}
				});
			},
			addCart() {
				// 单次购买限制
				if (this.detail.onelimittimes > 0 && this.detail.onelimittimes < this.buynumber) {
					this.$message.error(`每人单次只能购买${this.detail.onelimittimes}件`);
					return
				}
				// 库存不够
				if (this.detail.alllimittimes <= 0 ) {
					this.$message.error(`商品已售罄`);
					return
				}
				// 库存限制
				if (this.detail.alllimittimes > 0 && this.detail.alllimittimes < this.buynumber) {
					this.$message.error(`库存不足`);
					return
				}
				if (this.isInCart) {
					this.$message.error('该商品已经添加到购物车');
					return;
				}
		
				this.cart.buynumber = this.buynumber;
				this.cart.goodid = this.detail.id;
				this.cart.goodname = this.title;
				this.cart.tablename = this.tablename;
				this.cart.shangjiazhanghao = this.detail.shangjiazhanghao;
				this.cart.picture = this.detailBanner[0];
				this.cart.price = this.detail.price;
				this.cart.discountprice = this.detail.vipprice?this.detail.vipprice:this.detail.price;
				this.$http.post('cart/save', this.cart).then(res => {
					if (res.data.code === 0) {
						this.getCartList();
						this.$message({
							message: '添加成功',
							type: 'success',
							duration: 1500,
						});
					} else {
						this.$message.error(res.data.msg);
					}
				});
			},
			//立即购买
			buyNow() {
				// 单次购买限制
				if (this.detail.onelimittimes > 0 && this.detail.onelimittimes < this.buynumber) {
					this.$message.error(`每人单次只能购买${this.detail.onelimittimes}件`);
					return
				}
				// 库存不够
				if (this.detail.alllimittimes <= 0 ) {
					this.$message.error(`商品已售罄`);
					return
				}
				// 库存限制
				if (this.detail.alllimittimes > 0 && this.detail.alllimittimes < this.buynumber) {
					this.$message.error(`库存不足`);
					return
				}
				// 保存到storage中，在确认下单页面中获取要购买的商品
				localStorage.setItem('orderGoods', JSON.stringify([{
					tablename: this.tablename,
					goodid: this.detail.id,
					goodname: this.title,
					shangjiazhanghao: this.detail.shangjiazhanghao,

					picture:this.detailBanner[0],
					buynumber: this.buynumber,
					userid: Number(localStorage.getItem('frontUserid')),
					price: this.detail.price,
					discountprice: this.detail.vipprice ? this.detail.vipprice : this.detail.price
				}]));
				// 跳转到确认下单页面
				let query = {
					type: 1,
				}
				this.$router.push({path: '/index/shop-order/orderConfirm', query: query});
			},


			// 权限判断
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			// 修改
			editClick(){
				this.$router.push(`/index/shangpinxinxiAdd?type=edit&&id=${this.detail.id}`);
			},
			// 删除
			async delClick(){
				await this.$confirm('是否删除此商品信息？') .then(_ => {
					this.$http.post('shangpinxinxi/delete', [this.detail.id]).then(async res => {
						if (res.data.code == 0) {
							this.$http.get('storeup/list',{params:{
								page: 1,
								limit: 100,
								refid: this.detail.id,
								tablename: 'shangpinxinxi',
							}}).then(async obj=>{
								if(obj.data&&obj.data.code==0){
									let arr = []
									for(let x in obj.data.data.list){
										arr.push(obj.data.data.list[x].id)
									}
									if(arr.length){
										await this.$http.post('storeup/delete',arr).then(()=>{})
									}
									this.$message({
										type: 'success',
										message: '删除成功!',
										duration: 1500,
										onClose: () => {
											history.back()
										}
									});
								}
							})
						}
					});
				}).catch(_ => {});
			},
		
			chatClick() {
				let user = JSON.parse(localStorage.getItem('sessionForm'))
				if(localStorage.getItem('frontSessionTable')=='shangpinxinxi') {
					if(this.detail.shangjiazhanghao == user.shangjiazhanghao){
						this.$message.error('不能给自己发信息')
						return false
					}
				}
				this.$http.get('shangjia/query', {
					params: {
						shangjiazhanghao: this.detail.shangjiazhanghao
					}
				}).then(res => {
					if (res.data && res.data.code == 0) {
						this.fid = res.data.data.id
						this.initWebSocket(this.fid)
						this.fname = res.data.data.shangjiazhanghao
						if (res.data.data.touxiang) {
							this.fpic = res.data.data.touxiang.split(',')[0]
						} else if (res.data.data.headportrait) {
							this.fpic = res.data.data.headportrait.split(',')[0]
						}
						this.getChatList()
						this.chatVisible = true
					}
				})
			},
			saveGood(){
				this.showSaveType = false
				this.$http.get('friend/page', {
					params: {
						uid: Number(localStorage.getItem('frontUserid')),
						fid: this.fid,
					}
				}).then(obj => {
					if (obj.data && obj.data.code == 0) {
						if (!obj.data.data.list.length) {
							this.$http.post('friend/add', {
								uid: Number(localStorage.getItem('frontUserid')),
								fid: this.fid,
								name: this.fname,
								picture: this.fpic,
								type: 2,
								tablename:  'shangjia' ,
							}).then(res => {
								this.$http.post('friend/add', {
									uid: this.fid,
									fid: Number(localStorage.getItem('frontUserid')),
									type: 2,
									tablename: localStorage.getItem('frontSessionTable'),
									name: localStorage.getItem('username'),
									picture: this.mpic,
								}).then(res1 => {
				
								})
							})
						}
						this.$http.post('chatmessage/add', {
							uid: Number(localStorage.getItem('frontUserid')),
							fid: this.fid,
							content: this.detailBanner[0],
							format: 2
						}).then(res2 => {
							this.chatForm = {
								content: ''
							}
							this.getChatList()
						})
					}
				})
			},
			websocketOnopen: function() {
				
			},
			websocketOnmessage:function(e) {
				this.getChatList()
			},
			getChatList() {
				this.$http.get('chatmessage/mlist', {
					params: {
						page: 1,
						limit: 1000,
						uid: Number(localStorage.getItem('frontUserid')),
						fid: this.fid
					}
				}).then(res => {
					if (res.data && res.data.code == 0) {
						this.chatList = this.formatMessages(res.data.data.list)
						let div = document.getElementsByClassName('chat-content')[0]
						setTimeout(() => {
							if (div)
								div.scrollTop = div.scrollHeight
						}, 0)
					}
				})
			},
			formatMessages(messages) {
				let lastTime = null;
				messages.forEach((message, index) => {
					const currentTime = new Date(message.addtime).getTime();
					if (lastTime !== null) {
						const timeDiff = (currentTime - lastTime) / 1000 / 60; // 转换为分钟
						if (timeDiff < 3) {
							message.addtime = ''; // 如果小于3分钟，不显示时间
						}
					}
					lastTime = currentTime;
				});
				return messages;
			},
			timeFormat(time) {
				const Time = timeMethod.getTime(time).split("T");
				//当前消息日期属于周
				const week = timeMethod.getDateToWeek(time);
				//当前日期0时
				const nti = timeMethod.setTimeZero(timeMethod.getNowTime());
				//消息日期当天0时
				const mnti = timeMethod.setTimeZero(timeMethod.getTime(time));
				//计算日期差值
				const diffDate = timeMethod.calculateTime(nti, mnti);
				//本周一日期0时 （后面+1是去除当天时间）
				const fwnti = timeMethod.setTimeZero(timeMethod.countDateStr(-timeMethod.getDateToWeek(timeMethod
					.getNowTime()).weekID + 1));
				//计算周日期差值
				const diffWeek = timeMethod.calculateTime(mnti, fwnti);

				if (diffDate === 0) { //消息发送日期减去当天日期如果等于0则是当天时间
					return Time[1].slice(0, 5);
				} else if (diffDate < 172800000) { //当前日期减去消息发送日期小于2天（172800000ms）则是昨天-  一天最大差值前天凌晨00:00:00到今天晚上23:59:59
					return "昨天 " + Time[1].slice(0, 5);
				} else if (diffWeek >= 0) { //消息日期减去本周一日期大于0则是本周
					return week.weekName;
				} else { //其他时间则是日期
					return Time[0].slice(5, 10);
				}
			},
			clearChat(){
				this.websocketOnclose();
			},
			uploadSuccess(res) {
				if (res.code == 0) {
					this.addChat('upload/' + res.file)
				}
			},
			addChat(ask=null) {
				this.$http.get('friend/page', {
					params: {
						uid: Number(localStorage.getItem('frontUserid')),
						fid: this.fid,
					}
				}).then(obj => {
					if (obj.data && obj.data.code == 0) {
						if (!obj.data.data.list.length) {
							this.$http.post('friend/add', {
								uid: Number(localStorage.getItem('frontUserid')),
								fid: this.fid,
								name: this.fname,
								picture: this.fpic,
								type: 2,
								tablename:  'shangjia' ,
							}).then(res => {
								this.$http.post('friend/add', {
									uid: this.fid,
									fid: Number(localStorage.getItem('frontUserid')),
									type: 2,
									tablename: localStorage.getItem('frontSessionTable'),
									name: localStorage.getItem('username'),
									picture: this.mpic,
								}).then(res1 => {
			
								})
							})
						}
						this.$http.post('chatmessage/add', {
							uid: Number(localStorage.getItem('frontUserid')),
							fid: this.fid,
							content: ask?ask:this.chatForm.content,
							format: ask?2:1
						}).then(res2 => {
							this.websocketSend(ask?ask:this.chatForm.content)
							this.chatForm = {
								content: ''
							}
							this.getChatList()
						})
					}
				})
			},
		},
		components: {
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.detail-preview {
		padding: 20px calc((100% - 1200px)/2);
		margin: 0px auto;
		color: #666;
		align-content: flex-start;
		background: rgb(237, 237, 237);
		display: flex;
		width: 100%;
		font-size: 16px;
		justify-content: space-between;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.attr {
			padding: 30px;
			margin: 30px 0;
			background: #fff;
			display: block;
			width: 100%;
			position: relative;
			order: 2;
			.info {
				padding: 0;
				margin: 0;
				align-content: flex-start;
				background: none;
				display: flex;
				gap: 20px;
				width: 100%;
				justify-content: space-between;
				align-items: flex-start;
				flex-wrap: wrap;
				.title-item {
					border-radius: 5px;
					padding: 10px 6px;
					margin: 0;
					background: #fff;
					display: flex;
					width: 46%;
					border-color: rgb(25, 190, 212);
					border-width: 1px;
					line-height: 1.5;
					justify-content: space-between;
					align-items: center;
					border-style: solid;
					.detail-title {
						color: #000;
						font-weight: 600;
						font-size: 18px;
					}
					.colectBtn {
						cursor: pointer;
						border: 0px solid #ddd;
						border-radius: 8px;
						padding: 4px 10px;
						background: none;
						.icon {
							color: #666;
							font-size: inherit;
						}
						.text {
							color: #666;
							font-size: inherit;
						}
					}
					.colectBtnActive {
						border: 0px solid #fcbc78;
						color: #fc8108;
						background: none;
						.icon {
							color: #fc8108;
						}
						.text {
							color: #fc8108;
						}
					}
					.colectBtn:hover {
						border: 0px solid #fcbc78;
						color: #fc8108;
						background: none;
						.icon {
							color: #fc8108;
						}
						.text {
							color: #fc8108;
						}
					}
					.colectBtnActive:hover {
						border: 0px solid #fcbc78;
						color: #fc8108;
						background: none;
						.icon {
							color: #fc8108;
						}
						.text {
							color: #fc8108;
						}
					}
				}
				.item {
					border-radius: 5px;
					padding: 10px 6px !important;
					margin: 0;
					background: #fff;
					display: inline-flex;
					width: 46%;
					border-color: rgb(25, 190, 212);
					border-width: 1px;
					border-style: solid;
					.lable {
						padding: 0 10px 0 0;
						color: #333;
						white-space: nowrap;
						font-weight: 500;
						width: auto;
						font-size: inherit;
						line-height: 40px;
						text-align: right;
						height: auto;
					}
					.text {
						padding: 8px 10px 0;
						color: inherit;
						word-break: break-all;
						flex: 1;
						font-size: inherit;
						line-height: 24px;
						height: auto;
					}
					.price {
						color: #f00;
					}
					.bold {
						font-weight: bold;
					}
					.link {
						cursor: pointer;
						text-decoration: underline;
					}
				}
				.btn_box {
					padding: 10px 0;
					display: flex;
					width: 100%;
					font-size: 14px;
					flex-wrap: wrap;
				}
				.el-input-number {
					margin: 0 5px 0 0;
					display: inline-block;
					width: 180px;
					line-height: 38px;
					position: relative;
					.el-input-number__decrease:hover:not(.is-disabled)~.el-input .el-input__inner:not(.is-disabled), .el-input-number__increase:hover:not(.is-disabled)~.el-input .el-input__inner:not(.is-disabled) {
					  border-color: none;
					}
					/deep/ .el-input-number__decrease {
						cursor: pointer;
						z-index: 1;
						display: flex;
						border-color: #DCDFE6;
						border-radius: 4px 0 0 4px;
						top: 1px;
						left: 1px;
						background: rgb(25, 190, 212);
						width: 40px;
						justify-content: center;
						border-width: 0 1px 0 0;
						align-items: center;
						position: absolute;
						border-style: solid;
						text-align: center;
						height: 38px;
					}
					
					/deep/ .el-input-number__decrease i {
						color: #fff;
						font-size: 14px;
					}
				
					/deep/ .el-input-number__increase {
						cursor: pointer;
						z-index: 1;
						display: flex;
						border-color: #DCDFE6;
						right: 1px;
						border-radius: 0 4px 4px 0;
						top: 1px;
						background: rgb(25, 190, 212);
						width: 40px;
						justify-content: center;
						border-width: 0 0 0 1px;
						align-items: center;
						position: absolute;
						border-style: solid;
						text-align: center;
						height: 38px;
					}
					
					/deep/ .el-input-number__increase i {
						color: #fff;
						font-size: 14px;
					}
					
					/deep/ .el-input .el-input__inner {
						border: 1px solid #DCDFE6;
						border-radius: 4px;
						padding: 0 40px;
						outline: none;
						color: #666;
						background: #FFF;
						display: inline-block;
						width: 100%;
						font-size: 14px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
				}
				.addCartBtn {
					border: 0px solid rgba(33, 190, 50, .3);
					cursor: pointer;
					border-radius: 0px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #fff;
					background: rgb(25, 190, 212);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.addCartBtn:hover {
				}
				.buyBtn {
					border: 0px solid rgba(64, 158, 255, .3);
					cursor: pointer;
					border-radius: 0px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					color: #fff;
					background: rgb(39, 103, 205);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.buyBtn:hover {
				}
				.editBtn {
					border: 0px solid rgba(64, 158, 255, .3);
					cursor: pointer;
					border-radius: 0px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					color: #fff;
					background: rgb(93, 195, 190);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.editBtn:hover {
				}
				.delBtn {
					border: 0px solid rgba(209, 6, 2, .3);
					cursor: pointer;
					border-radius: 0px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #fff;
					background: rgb(33, 139, 208);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.delBtn:hover {
				}
				.chatBtn {
					border: 0px solid rgba(205, 138, 33, .3);
					cursor: pointer;
					border-radius: 0px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					color: #fff;
					background: rgb(69, 132, 233);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.chatBtn:hover {
				}
				.auditBtn {
					border: 0px solid rgba(209, 6, 2, .3);
					cursor: pointer;
					border-radius: 0px;
					padding: 0 10px;
					margin: 0 5px 0 0;
					outline: none;
					color: #fff;
					background: rgb(21, 191, 213);
					width: auto;
					font-size: inherit;
					line-height: 40px;
					height: 40px;
				}
				.auditBtn:hover {
				}
			}
		}
		.swiper3 {
			background: none;
			display: flex;
			width: 100%;
			height: auto;
			.big {
				border: 0px solid red;
				margin: 0 0 0px;
				background: #fff;
				width: calc(100% - 175px);
				position: relative;
				height: 488px;
				img {
					box-shadow: none;
					z-index: 1;
					top: 0px;
					left: 0px;
					object-fit: cover;
					display: block;
					width: 100%;
					position: absolute;
					height: 100%;
				}
			}
			.samll {
				padding: 128px 0px 0 10px;
				flex-direction: column;
				background: none;
				display: flex;
				gap: 10px;
				width: 165px;
				height: auto;
				.swiper3-small-item {
					border: 0px solid red;
					margin: 0 0px;
					flex: 1;
					background: #fff;
					position: relative;
					height: 100%;
					img {
						box-shadow: none;
						z-index: 1;
						top: 0px;
						left: 0px;
						object-fit: cover;
						display: block;
						width: 100%;
						position: absolute;
						height: 100%;
					}
				}
			}
		}
		.zancai {
			padding: 0;
			margin: 20px calc(50% - 600px);
			flex-direction: column;
			top: 0;
			background: none;
			display: flex;
			gap: 20px;
			width: 165px;
			justify-content: center;
			position: absolute;
			right: 0;
			order: 3;
			.zan {
				border: 0px solid #0674fc50;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0 0px 0 auto;
				color: rgb(172, 172, 172);
				background: rgb(245, 245, 245);
				display: flex;
				width: 165px;
				justify-content: center;
				align-items: center;
				height: auto;
				.icon {
					border-radius: 4px;
					padding: 0 5px;
					margin: 0;
					color: inherit;
					background: none;
					font-size: 24px;
					line-height: 44px;
				}
				.text {
					margin: 0 0 0 5px;
					color: inherit;
					font-size: inherit;
					opacity: 1;
				}
			}
			.zan:hover {
				.icon {
				}
				.text {
				}
			}
			.zanActive {
				color: rgb(39, 103, 205);
				.icon {
				}
				.text {
				}
			}
			.zanActive:hover {
				.icon {
				}
				.text {
				}
			}
			
			.cai {
				cursor: pointer;
				border: 0px solid #37c9e950;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0 0 0 auto;
				color: rgb(172, 172, 172);
				background: rgb(245, 245, 245);
				display: flex;
				width: 165px;
				justify-content: flex-end;
				align-items: center;
				.icon {
					padding: 0 5px;
					margin: 0;
					color: inherit;
					background: none;
					font-size: 24px;
					line-height: 44px;
					order: 2;
				}
				.text {
					margin: 0 5px 0 0;
					color: inherit;
					font-size: inherit;
				}
			}
			.cai:hover {
				.icon {
				}
				.text {
				}
			}
			.caiActive {
				color: rgb(39, 103, 205);
				.icon {
				}
				.text {
				}
			}
			.caiActive:hover {
				.icon {
				}
				.text {
				}
			}
		}
		.detail-tabs {
			border: 0px solid #2a3b5e50;
			border-radius: 8px;
			box-shadow: none;
			padding: 20px 20px;
			margin: 0;
			background: #fff;
			flex: 1;
			display: flex;
			width: 49%;
			flex-wrap: wrap;
			order: 4;
			& /deep/ .el-tabs__header .el-tabs__nav-wrap {
				margin-bottom: 0;
			}
			/deep/ .el-tabs__header {
				border-radius: 20px 20px 0 0;
				padding: 10px 20px;
				margin: 0;
				flex-direction: column;
				background: rgb(25, 190, 212);
				display: flex;
				width: 100%;
				border-color: #24355950;
				border-width: 0px;
				border-style: solid;
				flex-wrap: wrap;
			}
			
			/deep/ .el-tabs__header .el-tabs__item {
				border: 0px solid #ccc;
				padding: 0 0px;
				margin: 0 30px 0 0;
				color: #fff;
				font-weight: 500;
				display: inline-block;
				font-size: 16px;
				line-height: 30px;
				transition: all 0s;
				border-radius: 20px;
				background: none;
				width: 80px;
				position: relative;
				list-style: none;
				text-align: center;
				height: 30px;
			}
			
			/deep/ .el-tabs__header .el-tabs__item:hover {
				border: 0px solid #000;
				margin: 0 30px 0 0;
				color: #000;
				background: none;
				width: 80px;
				font-size: 16px;
				line-height: 30px;
				text-align: center;
				height: 30px;
			}
			
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				border: 0px solid #000;
				border-radius: 20px;
				margin: 0 30px 0 0;
				color: rgb(25, 190, 212);
				background: rgb(255, 255, 255);
				width: 80px;
				font-size: 16px;
				line-height: 30px;
				text-align: center;
				height: 30px;
			}
			
			/deep/ .el-tabs__content {
				border: 0px solid #24355950;
				padding: 30px 0;
				width: 100%;
			}
			.commentForm {
				box-shadow: none;
				padding: 0;
				margin: 20px 0;
				width: 100%;
				.item {
					padding: 0;
					display: flex;
					width: 100%;
					height: auto;
					/deep/ .el-form-item__label {
						padding: 0 10px 0 0;
						color: #666;
						white-space: nowrap;
						font-weight: 500;
						width: auto;
						font-size: inherit;
						line-height: 40px;
						text-align: right;
					}
					.editor {
						border: 1px solid #ccc;
						border-radius: 4px;
						box-shadow: 0 0 0px rgba(80, 80, 80, .2);
						color: #333;
						background: #fff;
						width: 100%;
						font-size: 14px;
						min-height: 350px;
						line-height: 32px;
						/deep/ .avatar-uploader {
							height: 0;
							line-height: 0;
						}
					}
				}
				.commentBtn {
					padding: 0;
					margin: 10px 0 0;
					width: 100%;
					text-align: right;
					height: auto;
					.submitBtn {
						border: 0;
						cursor: pointer;
						border-radius: 0px;
						padding: 0 20px 0 25px;
						margin: 0 20px 0 0;
						color: #fff;
						background: rgb(213, 103, 147);
						width: 170px;
						font-size: 15px;
						line-height: 40px;
						min-width: 110px;
						height: 40px;
					}
					.submitBtn:hover {
						opacity: 0.8;
					}
					.resetBtn {
						border: 0;
						cursor: pointer;
						border-radius: 0px;
						padding: 0 20px 0 25px;
						margin: 0 20px 0 0;
						color: #333;
						background: #c5c5c5;
						width: auto;
						font-size: 15px;
						line-height: 40px;
						min-width: 110px;
						height: 40px;
					}
					.resetBtn:hover {
						opacity: 0.8;
					}
				}
			}
			.comment-list {
				box-shadow: none;
				padding: 0;
				margin: 40px 0 0;
				display: flex;
				width: 100%;
				justify-content: space-between;
				flex-wrap: wrap;
				.comment-item {
					padding: 10px;
					margin: 0 0 20px;
					width: 100%;
					border-color: rgb(213, 103, 147);
					border-width: 1px;
					align-items: center;
					border-style: solid;
					height: auto;
					.istop {
						box-shadow: 0 4px 8px rgba(0,0,0,.1);
						top: 0;
						background: #fff;
						position: absolute;
						right: 10px;
						.icon {
							color: #000;
						}
					}
					.user {
						padding: 5px;
						background: #fff;
						display: flex;
						width: 100%;
						align-items: center;
						height: auto;
						.el-image {
							border-radius: 10px;
							margin: 0 10px 0 0;
							object-fit: cover;
							width: 60px;
							height: 60px;
						}
						.name {
							color: #333;
							font-weight: 600;
							font-size: 16px;
						}
					}
					.comment-time {
						color: #666;
					}
					.comment-content-box {
						border-radius: 4px;
						padding: 8px;
						margin: 10px 0px 0px;
						word-wrap: break-word;
						color: #888;
						background: none;
						font-size: 14px;
						line-height: 1.5;
						.zancai-box {
							margin: 8px 0 0 0;
							display: flex;
							width: 100%;
							justify-content: flex-end;
							align-items: center;
							height: 30px;
							.zan-item {
								display: flex;
								align-items: center;
								.icon {
								font-size: 14px;
								}
								.label {
								display: none;
								font-size: 14px;
								}
								.num {
								font-size: 14px;
								}
							}
							.zan-item.active {
								background: none;
								.icon {
								color: #ff0000;
								font-size: 14px;
								}
								.label {
								color: #ff0000;
								display: none;
								font-size: 14px;
								}
								.num {
								color: #ff0000;
								font-size: 14px;
								}
							}
							.zan-item:hover {
								opacity: 0.8;
								.icon {
								color: #ff000030;
								}
								.label {
								color: #ff000030;
								}
								.num {
								color: #ff000030;
								}
							}
							.cai-item {
								display: flex;
								align-items: center;
								.icon {
								font-size: 14px;
								}
								.label {
								display: none;
								font-size: 14px;
								}
								.num {
								font-size: 14px;
								}
							}
							.cai-item.active {
								background: none;
								.icon {
								color: #ff0000;
								font-size: 14px;
								}
								.label {
								color: #ff0000;
								display: none;
								font-size: 14px;
								}
								.num {
								color: #ff0000;
								font-size: 14px;
								}
							}
							.cai-item:hover {
								opacity: 0.8;
								.icon {
								color: #ff000030;
								}
								.label {
								color: #ff000030;
								}
								.num {
								color: #ff000030;
								}
							}
						}
						.comment-btn {
							margin: 8px 0 0 0;
							display: flex;
							width: 100%;
							justify-content: flex-end;
							align-items: center;
							height: 40px;
							.delBtn {
								border: 0;
								cursor: pointer;
								padding: 0 20px;
								margin: 0 0px 0 10px;
								color: #000;
								font-size: 14px;
								line-height: 32px;
								border-radius: 0px;
								outline: none;
								background: rgb(211, 211, 210);
								width: auto;
								min-width: 90px;
								height: 32px;
							}
						}
					}
				}
			}
		}
	}
	.share_view{
		box-shadow: 0 1px 6px rgba(0,0,0,.3);
		z-index: 11;
		bottom: 20%;
		background: #fff;
		position: fixed;
		right: 0;
		.share:last-of-type{
			border:none;
		}
	}
	.chat-content {
		padding-bottom: 20px;
		width: 100%;
		margin-bottom: 10px;
		max-height: 300px;
		height: 300px;
		overflow-y: scroll;
		border: 1px solid #eeeeee;
		background: #fff;
	
		.addtime {
			width: 100%;
			text-align: center;
			font-size: 12px;
		}
		
		.left-content {
			float: left;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
			display: flex;
			align-items: center;
		}
	
		.right-content {
			float: right;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
			display: flex;
			align-items: center;
		}
	}
	
	.clear-float {
		clear: both;
	}
	.idea1 {
		background: none;
		display: none;
		width: 100%;
		height: 1px;
		order: 4;
	}
	.idea2 {
		background: none;
		display: none;
		width: 100%;
		height: 1px;
		order: 6;
	}
</style>
