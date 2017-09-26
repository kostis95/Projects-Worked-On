 <!DOCTYPE html>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <link rel="stylesheet" type="text/css" href="style.css">
 <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
 
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
		<!--Navigation bar-->
		<div id="nav-placeholder"></div>
		<script>
		$(function(){
		  $("#nav-placeholder").load("navigation.html");
		});
		</script>

	</head>
	
	
	
  <body onload="init();">
	<h1>Take a snapshot of the current video stream</h1>
	
    <p>
	

    <button class="btn" onclick="startWebcam();">Start WebCam</button>
    <button class="btn" onclick="stopWebcam();">Stop WebCam</button> 
    <button class="btn" onclick="snapshot();">Take Snapshot</button> 

	<a href="#" class="button" id="btn-download">Download</a>
    </p>
    <video onclick="snapshot(this);" width=400 height=400 id="video" controls autoplay></video>
	<p> Screenshot : <p>
    <canvas  id="cnvs" width="400" height="350"></canvas>  
  </body>
  
  <script>
	var button = document.getElementById('btn-download');
	button.addEventListener('click', function (e) {
    var dataURL = canvas.toDataURL('image/png');
    button.href = dataURL;
	});

     // GET USER MEDIA CODE
    navigator.getUserMedia = ( navigator.getUserMedia ||
        navigator.webkitGetUserMedia ||
        navigator.mozGetUserMedia ||
        navigator.msGetUserMedia);

    var video;
    var webcamStream;

    function startWebcam() {
        if (navigator.getUserMedia) {
			navigator.getUserMedia (

            // constraints
            {
                video: true,
                audio: false
            },

            // successCallback
			//The LocalMediaStream interface represents a stream of media content fetched from 
			//a local data source. This is the interface returned by getUserMedia().
            function(localMediaStream) {
                video = document.querySelector('video');
                video.src = window.URL.createObjectURL(localMediaStream);
                webcamStream = localMediaStream.getTracks()[0];;
              },

              // errorCallback
              function(err) {
                 console.log("The following error occured: " + err);
              }
            );
        } else {
           console.log("getUserMedia not supported");
        }  
    }
	  
    function stopWebcam() {
        webcamStream.stop();
    }

	
    // TAKE A SNAPSHOT CODE
    var canvas, ctx;

    function init() {
        // Get the canvas and obtain a context for
        // drawing in it
        canvas = document.getElementById("cnvs");
		//"2d", leading to the creation of a CanvasRenderingContext2D object
        ctx = canvas.getContext('2d');
    }

    function snapshot() {
        // Draws current image from the video element into the canvas
        ctx.drawImage(video, 0,0, canvas.width, canvas.height);
    }
	  
		var c=document.getElementById("alpha");
		var d=c.toDataURL("image/png");
		var w=window.open('about:blank','image from canvas');
		w.document.write("<img src='"+d+"' alt='from canvas'/>");
		

  </script>
</html>