<template>
	<div>
		<div class="register-container">
			<el-form v-if="pageFlag=='register'" ref="ruleForm" class="rgs-form animate__animated animate__zoomIn" :model="ruleForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">基于协同过滤算法的跳蚤市场商品推荐系统</div>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('yonghuzhanghao')?'required':''">用户账号：</div>
						<el-input  v-model="ruleForm.yonghuzhanghao"  autocomplete="off" placeholder="用户账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('yonghuxingming')?'required':''">用户姓名：</div>
						<el-input  v-model="ruleForm.yonghuxingming"  autocomplete="off" placeholder="用户姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('shoujihaoma')?'required':''">手机号码：</div>
						<el-input  v-model="ruleForm.shoujihaoma"  autocomplete="off" placeholder="手机号码"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in yonghuxingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
						<el-input  v-model.number="ruleForm.nianling"  autocomplete="off" placeholder="年龄"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="yonghutouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shangjia'">
						<div class="lable" :class="changeRules('shangjiazhanghao')?'required':''">商家账号：</div>
						<el-input  v-model="ruleForm.shangjiazhanghao"  autocomplete="off" placeholder="商家账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shangjia'">
						<div class="lable" :class="changeRules('shangjiamingcheng')?'required':''">商家名称：</div>
						<el-input  v-model="ruleForm.shangjiamingcheng"  autocomplete="off" placeholder="商家名称"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shangjia'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shangjia'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shangjia'">
						<div class="lable" :class="changeRules('lianxifangshi')?'required':''">联系方式：</div>
						<el-input  v-model="ruleForm.lianxifangshi"  autocomplete="off" placeholder="联系方式"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shangjia'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="shangjiatouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="login()">注册</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import 'animate.css'
export default {
	data() {
		return {
			ruleForm: {
			},
			forgetForm: {},
            pageFlag : '',
			tableName:"",
			rules: {},
            yonghuxingbieOptions: [],
		};
	},
	mounted(){
		this.pageFlag = this.$route.query.pageFlag
		if(this.$route.query.pageFlag=='register'){
			
			let table = this.$storage.get("loginTable");
			this.tableName = table;
			if(this.tableName=='yonghu'){
				this.ruleForm = {
					yonghuzhanghao: '',
					yonghuxingming: '',
					mima: '',
					shoujihaoma: '',
					xingbie: '',
					nianling: '',
					touxiang: '',
					money: '',
				}
			}
			if(this.tableName=='shangjia'){
				this.ruleForm = {
					shangjiazhanghao: '',
					shangjiamingcheng: '',
					mima: '',
					lianxifangshi: '',
					touxiang: '',
					money: '',
				}
			}
			if ('yonghu' == this.tableName) {
				this.rules.yonghuzhanghao = [{ required: true, message: '请输入用户账号', trigger: 'blur' }]
			}
			if ('yonghu' == this.tableName) {
				this.rules.yonghuxingming = [{ required: true, message: '请输入用户姓名', trigger: 'blur' }]
			}
			if ('yonghu' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('shangjia' == this.tableName) {
				this.rules.shangjiazhanghao = [{ required: true, message: '请输入商家账号', trigger: 'blur' }]
			}
			if ('shangjia' == this.tableName) {
				this.rules.shangjiamingcheng = [{ required: true, message: '请输入商家名称', trigger: 'blur' }]
			}
			if ('shangjia' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			this.yonghuxingbieOptions = "男,女".split(',')
		}
	},
	created() {
	},
	destroyed() {
		  	},
	methods: {
		changeRules(name){
			if(this.rules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		close(){
			this.$router.push({ path: "/login" });
		},
        yonghutouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        shangjiatouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },

        // 多级联动参数


		// 注册
		login() {
			var url=this.tableName+"/register";
			if((!this.ruleForm.yonghuzhanghao) && `yonghu` == this.tableName){
				this.$message.error(`用户账号不能为空`);
				return
			}
			if((!this.ruleForm.yonghuxingming) && `yonghu` == this.tableName){
				this.$message.error(`用户姓名不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `yonghu` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `yonghu` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if(`yonghu` == this.tableName && this.ruleForm.shoujihaoma &&(!this.$validate.isMobile(this.ruleForm.shoujihaoma))){
				this.$message.error(`手机号码应输入手机格式`);
				return
			}
			if(`yonghu` == this.tableName && this.ruleForm.nianling &&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
				this.$message.error(`年龄应输入整数`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if((!this.ruleForm.shangjiazhanghao) && `shangjia` == this.tableName){
				this.$message.error(`商家账号不能为空`);
				return
			}
			if((!this.ruleForm.shangjiamingcheng) && `shangjia` == this.tableName){
				this.$message.error(`商家名称不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `shangjia` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `shangjia` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if(`shangjia` == this.tableName && this.ruleForm.lianxifangshi &&(!this.$validate.isMobile(this.ruleForm.lianxifangshi))){
				this.$message.error(`联系方式应输入手机格式`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			this.$http({
				url: url,
				method: "post",
				data:this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "注册成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.$router.replace({ path: "/login" });
						}
					});
				} else {
					this.$message.error(data.msg);
				}
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.register-container {
	position: relative;
	background: url(http://codegen.caihongy.cn/20240925/4b8262eef5784fd584baaba5b929a80a.webp);
	background-repeat: no-repeat;
	background-size: cover;
	background: url(http://codegen.caihongy.cn/20240925/4b8262eef5784fd584baaba5b929a80a.webp);
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: center;
	align-items: center;
	background-position: center center;
	.rgs-form {
		.rgs-form2 {
		width: 100%;
		}
		scrollbar-width: thin;
		padding: 0 0 0 44px;
		margin: auto;
		z-index: 1000;
		display: flex;
		flex-wrap: wrap;
		border-radius: 0;
		box-shadow: none;
		flex-direction: column;
		overflow: auto;
		background: url(http://codegen.caihongy.cn/20240925/d80b0ba5237c4ae48d1ae206fda80fb0.webp);
		width: 630px;
		align-items: flex-start;
		position: relative;
		height: 875px;
		.title {
			scrollbar-width: thin;
			padding: 0;
			margin: 20px 0 0 0;
			overflow: overlay;
			color: #000;
			background: none;
			font-weight: 600;
			width: 280px;
			font-size: 28px;
			min-height: 90px;
			text-align: left;
			height: auto;
		}
		.list-item {
			padding: 0;
			margin: 0 0 10px;
			display: flex;
			width: 430px;
			align-items: center;
			position: relative;
			flex-wrap: wrap;
			/deep/ .el-form-item__content {
			}
			.lable {
				padding: 0 0 0 14px;
				color: #333;
				width: 100%;
				font-size: 14px;
				line-height: 40px;
				text-align: left;
			}
			.el-input {
				width: 100%;
			}
			.el-input /deep/ .el-input__inner {
				border: 1px solid #ABABAB;
				border-radius: 5px;
				padding: 0 10px;
				outline: none;
				color: #000;
				width: 430px;
				font-size: 14px;
				height: 60px;
			}
			.el-input /deep/ .el-input__inner:focus {
				border: 1px solid #ABABAB;
				border-radius: 5px;
				padding: 0 10px;
				color: #000;
				width: 430px;
				font-size: 14px;
				height: 60px;
			}
			.el-input-number {
				width: 100%;
			}
			.el-input-number /deep/ .el-input__inner {
				text-align: center;
				border: 1px solid #ABABAB;
				border-radius: 5px;
				padding: 0 10px;
				outline: none;
				color: #000;
				width: 430px;
				font-size: 14px;
				height: 60px;
			}
			.el-input-number /deep/ .el-input__inner:focus {
				border: 1px solid #ABABAB;
				border-radius: 5px;
				padding: 0 10px;
				color: #000;
				width: 430px;
				font-size: 14px;
				height: 60px;
			}
			.el-input-number /deep/ .el-input-number__decrease {
				display: none;
			}
			.el-input-number /deep/ .el-input-number__increase {
				display: none;
			}
			.el-select {
				width: 100%;
			}
			.el-select /deep/ .el-input__inner {
				border: 1px solid #ABABAB;
				border-radius: 5px;
				padding: 0 10px;
				color: #000;
				width: 430px;
				font-size: 14px;
				height: 60px;
			}
			.el-select /deep/ .el-input__inner:focus {
				border: 1px solid #ABABAB;
				border-radius: 5px;
				padding: 0 10px;
				color: #000;
				width: 430px;
				font-size: 14px;
				height: 60px;
			}
			.el-date-editor {
				width: 100%;
			}
			.el-date-editor /deep/ .el-input__inner {
				border: 1px solid #ABABAB;
				border-radius: 5px;
				padding: 0 30px;
				color: #000;
				width: 430px;
				font-size: 14px;
				height: 60px;
			}
			.el-date-editor /deep/ .el-input__inner:focus {
				border: 1px solid #ABABAB;
				border-radius: 5px;
				padding: 0 30px;
				color: #000;
				width: 430px;
				font-size: 14px;
				height: 60px;
			}
			.el-date-editor.el-input {
				width: 100%;
			}
			/deep/ .el-upload--picture-card {
				background: transparent;
				border: 0;
				border-radius: 0;
				width: auto;
				height: auto;
				line-height: initial;
				vertical-align: middle;
			}
			/deep/ .upload .upload-img {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload-list .el-upload-list__item {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload .el-icon-plus {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload__tip {
				color: #666;
				font-size: 14px;
			}
			/deep/ .el-input__inner::placeholder {
				color: #9E9E9E;
				font-size: 14px;
			}
			.required {
				position: relative;
			}
			.required::after{
				color: red;
				left: 0;
				position: inherit;
				content: "*";
				order: -1;
			}
			.editor {
				background: #fff;
				width: 100%;
				height: auto;
			}
			.editor>.avatar-uploader {
				line-height: 0;
				height: 0;
			}
		}
		.list-item.email {
			input {
				border: 1px solid #ABABAB;
				border-radius: 4px 0 0 4px;
				padding: 0 10px;
				margin: 0;
				color: #000;
				background: #fff;
				width: 100%;
				font-size: 14px;
				height: 60px;
			}
			input:focus {
				border: 1px solid #ABABAB;
				border-radius: 4px 0 0 4px;
				padding: 0 10px;
				margin: 0;
				color: #000;
				background: #fff;
				flex: 1;
				width: 280x;
				font-size: 14px;
				height: 60px;
			}
			input::placeholder {
				color: #9E9E9E;
				font-size: 14px;
			}
			button {
				border: 0px solid #efeff7;
				cursor: pointer;
				border-radius: 0 4px 4px 0;
				padding: 0;
				margin: 1px 0 0;
				color: #333;
				background: #0d6efd20;
				width: 150px;
				font-size: 15px;
				height: 60px;
			}
			button:hover {
				opacity: 0.8;
			}
		}
		.register-btn {
			display: flex;
			width: 100%;
			align-items: center;
		}
		.register-btn1 {
			width: 100%;
		}
		.register-btn2 {
			width: 100%;
		}
		.r-btn {
			border: 0px solid rgba(0, 0, 0, 1);
			cursor: pointer;
			border-radius: 5px;
			padding: 0 10px;
			box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.3);
			margin: 40px 0 ;
			color: #fff;
			background: #5EC0CD;
			font-weight: 700;
			width: 190px;
			font-size: 24px;
			height: 64px;
		}
		.r-btn:hover {
			border: 0px solid rgba(0, 0, 0, 1);
			opacity: 0.8;
		}
		.r-login {
			cursor: pointer;
			padding: 0 70px 0 0;
			color: #666;
			display: inline-block;
			text-decoration: underline;
			width: 100%;
			font-size: 15px;
			line-height: 20px;
			text-align: right;
		}
		.r-login:hover {
			opacity: 1;
		}
	}
}
	
	::-webkit-scrollbar {
	  display: none;
	}
</style>
