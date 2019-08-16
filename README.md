## 麻将老师的springboot课程，跟着一步一步做的

##资料

##搭建Git环境步骤
+ 安装Git
+ 在IDEA中导入Git
+ 在Github中创建新的仓库，先不要创建README.md文件
+ 在IDEA的Terminal工具栏中输入git init命令，把该项目仓库变成Git管理的仓库
+ 查看.gitignore文件，看看是否有除代码其他的文件会被提交上去
+ 修改本项目下的./git/config  添加[user] name=XXX  email=XXX
+ git add .    把目录下所有的文件提交到仓库
+ git status   查看所有文件的状态（未提交的标红）
+ git commit - m "init repo"   把项目提交到本地仓库  备注是"init repo"
+ git remote add origin https://github.com/19970711/community.git 连接本地仓库到我的远程github地址
+ git push -u origin master  把项目提交到远程的origin的master分支

## Github的第三方登录
[Github登录的参考文档](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)
访问逻辑步骤：
+ 1.用户访问应用
+ 2.用户点击第三方登录
+ 3.请求GET https://github.com/login/oauth/authorize地址
+ 4.Github会根据已经备案的redirect_url的回调地址，携带code返回给码匠社区
+ 5.码匠社区通过POST https://github.com/login/oauth/access_token携带code发送请求给github网站
+ 6.返回access_token给麻将社区
+ 7.码匠社区调用GET https://api.github.com/user传入access_token参数请求github
+ 8.github把user信息返回给码匠社区
+ 9.码匠社区把user信息存入数据库，更新登录状态
+ 10.返回给用户登录状态


