function PhoneListItemComponent(specs, container) {
    this.id = specs.id;
    this.contactId = specs.contactId;
    this.countryCode = specs.countryCode;
    this.operatorCode = specs.operatorCode;
    this.phoneNumber = specs.phoneNumber;
    this.comment = specs.comment;
    this.phoneType = specs.phoneType;
    this.container = container;
}

document.addEventListener("click", function (event) {
    if (event.target.classList.contains('phones-list__link')){
        event.preventDefault();
        event.stopPropagation();
        createGETPromiseRequest(event.target.href, (result) => JSON.parse(result))
            .then(
                function (phone){
                    let modal_body = document.getElementById("body");
                    let phoneComponent = new PhoneComponent(phone, modal_body);
                    let rendered = phoneComponent.render();
                    modal.show();

                },
                function (error){
                    alert("Can't load data. Please wait 1 min and try again");
                }
            );
    }
});

PhoneListItemComponent.prototype.render = function () {
    let container = this.container;
    let  template = document.getElementById("phoneListItemTemplate").innerHTML;
    let rendered = _.template(template)({"phone": this});
    container.innerHTML += rendered;
    this.container.lastElementChild.setAttribute("data-phoneid", this.id);

};


