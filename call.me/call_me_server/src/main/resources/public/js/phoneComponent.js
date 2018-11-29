const emptyPhoneData = {
    countryCode: null,
    operatorsCode: null,
    phoneNumber: null,
    comment: "",
    phoneType: "MOBILE",
};


function Phone(specs) {
    this.id = specs.id;
    this.contactId = specs.contactId;
    this.countryCode = specs.countryCode;
    this.operatorCode = specs.operatorCode;
    this.phoneNumber = specs.phoneNumber;
    this.comment = specs.comment;
    this.phoneType = specs.phoneType;
};

function PhoneComponent(specs, container) {
    if (specs) {
        this.id = specs.id;
        this.contactId = specs.contactId;
        this.countryCode = specs.countryCode;
        this.operatorCode = specs.operatorCode;
        this.phoneNumber = specs.phoneNumber;
        this.comment = specs.comment;
        this.phoneType = specs.phoneType;
    }
    this.container = container;
}


const onClosePhoneClickDefault = function (contactId) {
    createGETPromiseRequest(getContactURL + contactId, (result) => JSON.parse(result))
        .then(
        function (contact){
            let contactComponent = new ContactComponent(contact, appContent);
            let rendered = contactComponent.render();
            modal.hide();
        },
        function (error){
            alert("Can't load data. Please wait 1 min and try again");
        }
    );
};


PhoneComponent.prototype.render = function () {
    let phoneComponent = this;
    let template = document.getElementById("phoneTemplate").innerHTML;
    let container = this.container;
    let rendered = _.template(template)({"phone": this});
    container.innerHTML = rendered;
    document.getElementById("phone-form").setAttribute("data-contactId", phoneComponent.contactId);
    document.getElementById("phone-form").setAttribute("data-id", phoneComponent.id);
};


PhoneComponent.prototype.renderToAdd = function () {
    let template = document.getElementById("phoneTemplate").innerHTML;
    let container = this.container;
    let rendered = _.template(template)({"phone": null});
    container.innerHTML = rendered;
    document.getElementById("phone-form").setAttribute("data-contactId",
        document.getElementById("contact-form").getAttribute("data-id"));
    return PhoneComponent;


};


const validatePhone = function (phone) {
    if(!instanceCheckers.isString(phone.countryCode) ||
        phone.countryCode.length > 5 ||
        phone.countryCode.length === 0){
        alert("please, specify a country code!");
        return false;
    }
    if(!instanceCheckers.isString(phone.operatorCode) ||
        phone.operatorCode.length > 5 ||
        phone.operatorCode.length === 0) {
        alert("please, specify a operator code!");
        return false;
    }
    if(!instanceCheckers.isString(phone.phoneNumber) ||
        phone.phoneNumber.length > 9 ||
        phone.phoneNumber.length === 0) {
        alert("please, specify a country phone!");
        return false;
    }

    if(!instanceCheckers.isString(phone.comment) ||
        phone.comment.length > 256)
    {
        alert( "Please provide your surname!" );
        return false;
    }
    return true;
};

document.addEventListener("click", function (event) {
    if (event.target.id === "updatePhoneButton") {
        event.preventDefault();
        event.stopPropagation();
        let form = toObject(document.getElementById("phone-form"));
        form["id"] = document.getElementById("phone-form").getAttribute("data-id");
        form["contactId"] = document.getElementById("phone-form").getAttribute("data-contactId");
        let phone = new Phone(form);
        if(!validatePhone(phone)){
            return false;
        }
        createPUTPromiseRequest(updatePhoneURL, phone)
            .then(
                function (result){
                    onClosePhoneClickDefault(phone.contactId);
                },
                function (error){
                    alert("Can't load data. Please wait 1 min and try again");
                }
            );
    }
});

document.addEventListener("click", function (event) {
    if (event.target.id === "addPhoneButton") {
        event.preventDefault();
        event.stopPropagation();
        var form = document.getElementById("phone-form");
        form = toObject(form);
        form["contactId"] = document.getElementById("phone-form").getAttribute("data-contactId");
        let phone = new Phone(form);
        if(!validatePhone(phone)){
            return false;
        }
        createPOSTPromiseRequest(createPhoneURL, phone)
            .then(
                function (result){
                    onClosePhoneClickDefault(phone.contactId);
                },
                function (error){
                    alert("Can't load data. Please wait 1 min and try again");
                }
            );
    }
});