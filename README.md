# install
### Add it in your root build.gradle at the end of repositories:
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
### Add the dependency
    dependencies {
	  implementation 'com.github.kuenfu:customdialog:V1.2'
	}
  
  


# Usage
     val customDialog = CustomDialog(this)
     customDialog.setTitle("title").setMessage("message").setOnClickBottomListener(object : CustomDialog.OnClickBottomListener {
         override fun onPositiveClick() {
             customDialog.dismiss()
         }

         override fun onNegtiveClick() {
             customDialog.dismiss()
         }
     }).show()

# demo

<img width="350" src="https://github.com/kuenfu/customdialog/blob/master/Screenshot_1558800074.png"/>
