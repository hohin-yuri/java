function AttachmentListItemComponent(specs, container) {
    this.id = specs.id;
    this.contactId = specs.contactId;
    this.comment = specs.comment;
    this.uploaded = specs.uploaded;
    this.filename = specs.filename;
    this.container = container;
}

AttachmentListItemComponent.prototype.render = function () {
    let container = this.container;
    let  template = document.getElementById("attachmentListItemTemplate").innerHTML;
    let rendered = _.template(template)({"attachment": this});
    container.innerHTML += rendered;
    this.container.lastElementChild.setAttribute("data-attachid", this.id);

};
