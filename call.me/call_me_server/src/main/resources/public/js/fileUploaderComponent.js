function FileUploadComponent(contactId, container) {
    this.contactId = contactId;
    this.container = container;
}

FileUploadComponent.prototype.render = function () {
    let component = this;
    let template = document.getElementById("fileUploaderComponentTemplate").innerHTML;

    this.container.innerHTML = template;
    document.getElementById("uploadForm").setAttribute("data-id", component.contactId);

    return FileUploadComponent;
};

const validateAttachment = function (attachment) {
    if(!instanceCheckers.isString(attachment.comment.value) ||
        attachment.comment.value.length > 256)
    {
        alert( "Please provide your comment!" );
        return false;
    }
    if (document.getElementById("fileInput").files[0].size / 1024 / 1024 > 1){
        alert("Incorrect file size!");
        return false;
    }
    return true;
};

let upload = function (progress) {
    let xhr = new XMLHttpRequest();

    xhr.onload = xhr.onerror = function() {
        if (this.status === 200) {
            modal.hide();
            createGETPromiseRequest(getContactAttachmentsURL
                + document.getElementById("uploadForm").getAttribute("data-id"), (jsonData) => JSON.parse(jsonData))
                .then(
                    function (attachmnets){
                        let element = document.getElementById("attachments-menu");
                        console.log(element);
                        element.parentNode.removeChild(element);
                        element = document.getElementById("attachments-list");
                        element.parentNode.removeChild(element);
                        let phonesList = new AttachmentListComponent(attachmnets, appContent);
                        phonesList.render();
                    },
                    function (error){
                        alert("Can't load data. Please wait 1 min and try again");
                    }
                );
        } else {
            console.log("error " + this.status);
        }
    };

    xhr.upload.onprogress = function(event) {
        progress.innerHTML = event.loaded + ' / ' + event.total;
    };

    xhr.open("POST", addAttachmentURL + document.getElementById("uploadForm").getAttribute("data-id"), true);
    var form = document.getElementById("uploadForm");
    if(!validateAttachment(form)){
        return false;
    }
    var formData = new FormData(form);
    xhr.send(formData);
};

document.addEventListener("click", function(event) {
    if (event.target.id === "addAttachmentButton") {
        event.preventDefault();
        event.stopPropagation();
        let file = document.getElementById("fileInput").files[0];
        if (file) {
            let progress = document.getElementById("fileUploaderComponentTemplate").lastChild;
            upload(progress);
        }
        return false;
    }
});