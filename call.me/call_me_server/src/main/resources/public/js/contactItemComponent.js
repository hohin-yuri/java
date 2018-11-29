
    function ContactListItemComponent(specs, container) {
        this.container = container;
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

    document.addEventListener("click", function (event) {
        if (event.target.classList.contains("contacts-list__link")) {
            event.preventDefault();
            event.stopPropagation();

            createGETPromiseRequest(event.target.href, (result) => JSON.parse(result))
                .then(
                    function (contact){
                        console.log(contact);
                        let contactComponent = new ContactComponent(contact, appContent);
                        let rendered = contactComponent.render();
                    },
                    function (error){
                        alert("Can't load data. Please wait 1 min and try again");
                    }
                );
        }
    });

    ContactListItemComponent.prototype.render = function () {
        let container = this.container;
        let  template = document.getElementById("contactListItemTemplate").innerHTML;
        let rendered = _.template(template)({"contact": this});
        container.innerHTML += rendered;
        this.container.lastElementChild.setAttribute("data-contactid", this.id);
        this.container.lastElementChild.setAttribute("data-email", this.email);
    };




