package com.pranav.bpapp.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.pranav.bpapp.R
import com.pranav.bpapp.controller.ProcessResponceInterphase
import com.pranav.bpapp.controller.request.GetRequest
import com.pranav.bpapp.controller.request.ImageRequest
import com.pranav.bpapp.controller.request.PostRequest
import com.pranav.bpapp.controller.responce.Response
import com.theartofdev.edmodo.cropper.CropImage
import java.io.File

class MainActivityFragment : Fragment() {

    lateinit var imgVwCameraClick: ImageView
    private val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
    var imgDecodableString = ""
    var finalFile: File? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.activity_main_fragment, container, false)

//        imgVwCameraClick = mView.findViewById(R.id.imgVwCameraClick)

//        PostRequest(activity!!, PostResponceProcessor()).loginVerify("dummy_mail")
//        GetRequest(activity!!, GetResponceProcessor()).feeds("test_string", 1)
//        ImageRequest(activity!!, ImageResponceProcessor()).uploadFeed("test_string_one","test_string_two", null)

        imgVwCameraClick.setOnClickListener {
            if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity!!, permissions, 101)
                    Toast.makeText(activity, "permission denied accessing camera", Toast.LENGTH_SHORT).show()
                }
                try {
                    CropImage.activity()
                        .start(activity!!, this)
                } catch (e: Exception) {
                }

            } else {
                if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(activity!!, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity!!, permissions, 101)
                }
                Toast.makeText(activity, "permission denied accessing camera and gallery", Toast.LENGTH_SHORT).show()
            }
        }

        return mView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                val results = CropImage.getActivityResult(data)
                val resultUri = results.uri

                finalFile = File(resultUri.path)
                imgDecodableString = finalFile.toString()

                imgVwCameraClick.setPadding(0, 0, 0, 0)
                imgVwCameraClick.setImageURI(resultUri)


            }
        } catch (e: Exception) {


        }


    }

    inner class ImageResponceProcessor :
        ProcessResponceInterphase<Response> {
        override fun processResponce(responce: Response) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun processResponceError(responce: Any?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    inner class GetResponceProcessor :
        ProcessResponceInterphase<Response> {
        override fun processResponce(responce: Response) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun processResponceError(responce: Any?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }


    inner class PostResponceProcessor :
        ProcessResponceInterphase<Response> {
        override fun processResponce(responce: Response) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun processResponceError(responce: Any?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}


