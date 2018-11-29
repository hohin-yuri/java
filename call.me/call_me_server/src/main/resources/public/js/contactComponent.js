function toObject(form) {
    var obj = {};
    var elements = form.querySelectorAll( "input, select, textarea" );
    for( var i = 0; i < elements.length; ++i ) {
        var element = elements[i];
        var name = element.name;
        var value = element.value;

        if(name) {
            obj[name] = value;
        }
    }

    return obj;
}

function Contact(specs) {
    this.id = specs.id;
    this.firstName = specs.firstName;
    this.secondName = specs.secondName;
    this.surname = specs.surname;
    this.birthday = new Date(specs.birthday);
    this.gender = specs.gender;
    this.citizenship = specs.citizenship;
    this.materialStatus = specs.materialStatus;
    this.email = specs.email;
    this.website = specs.website;
    this.job = specs.job;
    this.country = specs.country;
    this.city = specs.city;
    this.street = specs.street;
    this.apartment = specs.apartment;
    this.zipCode = specs.zipCode;
};

function ContactComponent(specs, container) {
    this.container = container;
    if (specs) {
        this.id = specs.id;
        this.firstName = specs.firstName;
        this.secondName = specs.secondName;
        this.surname = specs.surname;
        this.birthday = new Date(specs.birthday);
        this.gender = specs.gender;
        this.citizenship = specs.citizenship;
        this.materialStatus = specs.materialStatus;
        this.email = specs.email;
        this.website = specs.website;
        this.job = specs.job;
        this.country = specs.country;
        this.city = specs.city;
        this.street = specs.street;
        this.apartment = specs.apartment;
        this.zipCode = specs.zipCode;
    }
}


const onCloseClickDefault = function () {
    getContacts(1).then(
        function (contacts){
            let contactComponent = new ContactListComponent(contacts, appContent);
            let rendered = contactComponent.render();
        },
        function (error){
            alert("Can't load data. Please wait 1 min and try again");
        }
    );
};

document.addEventListener("click", function (event) {
    if (event.target.id === "cancelButton") {
        event.preventDefault();
        event.stopPropagation();
        onCloseClickDefault();
    }
});

document.addEventListener("click", function (event) {
    if (event.target.id === "addContactButton") {
        event.preventDefault();
        event.stopPropagation();
        let form = toObject(document.getElementById("contact-form"));
        let contact = new Contact(form);
        if(!validateContact(contact)){
            return false;
        }
        createPOSTPromiseRequest(createContactURL, contact)
            .then(
                function (result){
                    onCloseClickDefault();
                },
                function (error){
                    alert("Can't load data. Please wait 1 min and try again");
                }
            );
    }
});

document.addEventListener("click", function (event) {
    if (event.target.id === "updateContactButton") {
        event.preventDefault();
        event.stopPropagation();
        var form = document.getElementById("contact-form");
        var contactId = parseInt(form.getAttribute("data-id"));
        form = toObject(form);
        form["id"] = contactId;
        let contact = new Contact(form);
        if(!validateContact(contact)){
            return false;
        }
        createPUTPromiseRequest(updateContactURL, contact)
            .then(
                function (result){
                    onCloseClickDefault();
                },
                function (error){
                    alert("Can't load data. Please wait 1 min and try again");
                }
            );
    }
});


ContactComponent.prototype.render = function () {
    let contactComponent = this;
    let template = document.getElementById("contactComponentTemplate").innerHTML;
    let container = this.container;
    let rendered = _.template(template)({"contact": this});
    container.innerHTML = rendered;

    createGETPromiseRequest(getContactPhonesURL + this.id, (jsonData) => JSON.parse(jsonData))
        .then(
        function (phones){
            let phonesList = new PhonesListComponent(phones, container);
            phonesList.render();
        },
        function (error){
            alert("Can't load data. Please wait 1 min and try again");
        }
    );
    createGETPromiseRequest(getContactAttachmentsURL + this.id, (jsonData) => JSON.parse(jsonData))
        .then(
            function (attachmnets){
                let phonesList = new AttachmentListComponent(attachmnets, container);
                phonesList.render();
            },
            function (error){
                alert("Can't load data. Please wait 1 min and try again");
            }
    );

    let avatarComponent = new AvatarComponent(this.id, document.getElementById("avatar-container"));
    avatarComponent.render();



    return ContactComponent;
};

ContactComponent.prototype.renderToAdd = function () {
    let contactComponent = this;
    let template = document.getElementById("contactComponentTemplate").innerHTML;
    let container = this.container;
    let rendered = _.template(template)({"contact": null});
    container.innerHTML = rendered;

    return ContactComponent;


};

const validateContact = function (contact) {
    if(!instanceCheckers.isString(contact.firstName) ||
        contact.firstName === "" ||
        contact.firstName.length > 50)
    {
        alert("Please provide your name!");
        return false;
    }

    if(!instanceCheckers.isString(contact.secondName) ||
        contact.secondName === "" ||
        contact.secondName.length > 50)
    {
        alert( "Please provide your second name!" );
        document.myForm.EMail.focus() ;
        return false;
    }
    if(!instanceCheckers.isString(contact.surname) ||
        contact.surname.length > 50)
    {
        alert( "Please provide your surname!" );
        return false;
    }
    if(!(contact.gender in GenderEnum))
    {
        alert( "Please provide your gender!" );
        return false;
    }
    if(!(contact.materialStatus in MaterialStatusEnum))
    {
        alert( "Please provide your materialStatus!" );
        return false;
    }
    if(instanceCheckers.isDate(contact.birthday)){
        if(contact.birthday > Date.now() ||
            contact.surname.length > 50)
        {
            alert( "Please provide your birthday!" );
            return false;
        }
    }
    else {
        alert("Please provide your birthday!");
        return false;
    }
    if(!instanceCheckers.isString(contact.email) ||
        contact.email === "" ||
        !validateEmail(contact.email) ||
        contact.email.length > 250)
    {
        alert( "Please provide your Email!" );
        return false;
    }
    if(!instanceCheckers.isString(contact.citizenship) ||
        contact.email.length > 256)
    {
        alert("Please provide your Citezenship!");
        return false;
    }
    return true;
};

function validateEmail(email)
{
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

