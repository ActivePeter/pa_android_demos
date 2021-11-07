# pa_android_demos
这里用来存放一些我安卓项目的demo，以备以后使用

### 项目列表

1. 1.一个opencv + c++的项目，使用camerax进行摄像头帧数据的捕捉，然后使用surfaceView进行绘制。

   cameraX为一个较为完善的安卓新的摄像头接口，可以解耦三种摄像头数据获取，我用的是analyzer，直接拿到原图像，

   surfaceView为一种脱离出主线程的界面渲染，对于实时更新的画面较宜采用

   #### 程序解耦：

   分为了CameraHandler,CameraViewHandler,IPreviewInCallback,

   第一个为处理摄像头操作相关的，第二个为更新摄像头预览画面相关的，第三个是一个回调接口，camera handler会对所有注册的callback实现进行回调，传回一帧经过转码的图片

2. 

### 记录一些问题：

1.之前装最新版as （4.2）最后会出问题，现在换成了3.6版，没什么问题

2.启动时提示proxy不要管。直接跳过即可.如果设置出了问题，需要去.gradle里找配置文件，把相关proxy配置手动删除

