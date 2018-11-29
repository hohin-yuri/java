function AttachmentListComponent(attachments, container) {
    this.container = container;
    this.attachments = attachments;
}

document.addEventListener("click", function (event) {
    if (event.target.id === "delete-attachments") {
        event.preventDefault();
        event.stopPropagation();
        let container = document.getElementById("attachments-list");
        let checkedElements = container.querySelectorAll("input[type='checkbox']:checked");
        if(checkedElements.length === 0) {
            alert("Sorry, by there are no checked elements!");
            return;
        }
        for(let elem of checkedElements) {
            createDELETEPromiseRequest(
                deleteAttachmentURL + elem.parentElement.getAttribute("data-attachid"), elem.parentElement);
        }
    }
});

document.addEventListener("click", function (event) {
    if (event.target.id === "add-attachment") {
        event.preventDefault();
        event.stopPropagation();
        let modalBody = document.getElementById("body");
        let form = document.getElementById("contact-form");
        let contactId = parseInt(form.getAttribute("data-id"));
        let uploadComponent = new FileUploadComponent(contactId, modalBody);
        uploadComponent.render();
        modal.show();
    }
});

AttachmentListComponent.prototype.render = function () {
    let template = document.getElementById("attachmentsListTemplate");
    document.getElementById("attachments-list-container").innerHTML += template.innerHTML;
    let attContainer = document.getElementById("attachments-list");

    for (let att of this.attachments) {
        let item = new AttachmentListItemComponent(att, attContainer);
        item.render();
    }

    return AttachmentListComponent;
};
