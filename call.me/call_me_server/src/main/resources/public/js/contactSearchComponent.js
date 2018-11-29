function ContactSearch(specs) {
    this.firstName = specs.firstName;
    this.secondName = specs.secondName;
    this.surname = specs.surname;
    this.birthdayFrom = new Date(specs.birthdayFrom);
    this.birthdayTo = new Date(specs.birthdayTo);
    this.gender = specs.gender;
    this.citizenship = specs.citizenship;
    this.materialStatus = specs.materialStatus;
    this.country = specs.country;
    this.city = specs.city;
    this.street = specs.street;
    this.apartment = specs.apartment;
    this.zipCode = specs.zipCode;
}

function ContactSearchComponent(specs, container) {
    this.container = container;
    if(specs) {
        this.firstName = specs.firstName;
        this.secondName = specs.secondName;
        this.surname = specs.surname;
        this.birthdayFrom = new Date(specs.birthdayFrom);
        this.birthdayTo = new Date(specs.birthdayTo);
        this.gender = specs.gender;
        this.citizenship = specs.citizenship;
        this.materialStatus = specs.materialStatus;
        this.country = specs.country;
        this.city = specs.city;
        this.street = specs.street;
        this.apartment = specs.apartment;
        this.zipCode = specs.zipCode;
    }
}


document.addEventListener("click", function (event) {
    if (event.target.id === "searchContactButton") {
        event.preventDefault();
        event.stopPropagation();
        let form = toObject(document.getElementById("contact-search-form"));
        let contactSearch = new ContactSearch(form);
        createPOSTPromiseRequest(searchContactsURL, contactSearch)
            .then(
                function (result){
                    let contacts = JSON.parse(result);
                    let contactComponent = new ContactListComponent(contacts, appContent, null);
                    let rendered = contactComponent.render();
                },
                function (error){
                    alert("Can't load data. Please wait 1 min and try again");
                }
            );
    }
});


document.addEventListener("click", function (event) {
    if (event.target.id === "search-contact" ||
        event.target.classList.contains("fa-search")) {
        event.preventDefault();
        event.stopPropagation();
        let contactSearchComponent = new ContactSearchComponent(null, appContent);
        let rendered = contactSearchComponent.render();
    }
});

ContactSearchComponent.prototype.render = function () {
    let contactSearchComponent = this;
    let template = document.getElementById("contactSearchComponentTemplate").innerHTML;
    let container = this.container;
    container.innerHTML = template;
    return ContactSearchComponent;
};

ContactSearchComponent.prototype.validate = function (contact) {
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
    if(!instanceCheckers.isDate(contact.birthdayFrom) ||
        contact.birthdayFrom >= Date.now())
    {
        alert( "Please provide your birthday!" );
        return false;
    }
    if(!instanceCheckers.isDate(contact.birthdayTo) ||
        contact.birthdayTo >= Date.now())
    {
        alert( "Please provide your birthday!" );
        return false;
    }
    if(instanceCheckers.isDate(contact.birthdayFrom) &&
        instanceCheckers.isDate(contact.birthdayTo) &&
        contact.birthdayFrom > contact.birthdayTo)
    {
        alert( "Please provide your birthday!" );
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

