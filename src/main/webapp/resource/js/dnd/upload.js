window.addEventListener("load",function(){
    var section = document.querySelector("#upload");
    var dropzone = section.querySelector(".drop-zone");
    var percenrSpan = section.querySelector(".percent");
    var progressDiv = section.querySelector(".progress");

    dropzone.addEventListener("dragenter", function(e){
        e.preventDefault()
        console.log("들어왔니");

        // for(var key in e.dataTransfer.types)
        //     console.log(key);

        //console.log(e.dataTransfer.types);

        if(e.dataTransfer.types[0]=="Files")
        {
            console.log("드랍하세요");
            dropzone.classList.add("valide");
        }
        else
        {
        console.log("유효하지않음");
        dropzone.classList.remove("valide");
        dropzone.classList.add("invailde");
        }
    });

    dropzone.addEventListener("dragleave", function(e){
        e.preventDefault()
        console.log("나갔니");
        dropzone.classList.remove("valide");
        dropzone.classList.remove("invailde");
    });

    dropzone.addEventListener("dragover", function(e){
        e.preventDefault()
        console.log("들어오고있니");
    });
    
    var fileButton = dropzone.querySelector("input[type=file]");
    var trigButton = dropzone.querySelector(".file-selector");
    trigButton.onclick = function (e) {

        var event = new MouseEvent(
            "click",
            {
                view: window,
                bubbles: true,
                cancelable: true
            }
        );

        fileButton.dispatchEvent(event);

    }

    dropzone.addEventListener("drop", function(e){
        e.preventDefault()//놓자마자 바로 열어버림. 기본행위 막음
        console.log("drop");

         var files = e.dataTransfer.files;
         var size = files.length;

         if(size >1 ){
             alert("파일은 하나씩만 업로드 할 수 있습니다.");
             return;
         }

         var file = files[0];
         var regex = new RegExp("image/(jpg|jpeg|png)")
         if(!file.type.match(regex)){
             alert("파일 형식 확인");
             return;
         }

        //  if(file.size > 10*1024*1024){
        //      alert("10메가 이상의 파일은 업로드 할 수 없습니다.");
        //      return;
        //  }
        
        //id=newlect&pwd=111-> only String
        var formData = new FormData();
        formData.append("file",file);
        
        
        var request = new XMLHttpRequest();
        request.addEventListener("load",function(e){
        	//alert(request.responseText);
        	if(request.responseText == "ok"){
        		//목록을 새로 요청하기
        		 var req = new XMLHttpRequest();
        		 req.addEventListener("load",function(e){
        			 alert(req.responseText);
        		 });
        		 req.open("GET","../../../file-list");
        		 req.send();
        	}
        });
        request.upload.addEventListener("progress", function(e){
            //loaded, total
        //     console.log(Math.round(e.loaded*100 / e.total));
        //    percenrSpan.innerHTML = Math.round(e.loaded*100 / e.total);
        //    progressDiv.style.width = Math.round(e.loaded*100 / e.total)+"%";
            if(e.lengthComputable){
                var degree = Math.round(e.loaded*100 / e.total);
                percenrSpan.innerHTML = degree;
                progressDiv.style.width = degree+"%";
            }
        });
        request.open("POST", "../../../upload");
        request.send(formData);//동기형으로 하면 여기서 멈춤현상
        /* 
        load
        error
        abort
        uploadProgress
        */
    });
//     dropzone에서 사용되는 이벤트
//     dragenter: 드래그된 리소스가 dragzone에 들어올때
//     dragleave: dropzone에 들어왔던 드래그가 영역 밖으로 나갈때
//     dragover:dropzone에서 드래그 상태로 이동 중일때
//     drop: dropzone에서 드롭할때
// 
});