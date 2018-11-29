function ContactListComponent(contacts, container, pageNumber=1) {
    this.container = container;
    this.contacts = contacts;
    this.pageNumber = pageNumber;
}
document.addEventListener("click", function (event) {
    if (event.target.id.includes("pagination")) {
        event.preventDefault();
        event.stopPropagation();
        createGETPromiseRequest(event.target.href, (jsonData) => JSON.parse(jsonData))
            .then(
                function (contacts){
                    let page = document.getElementById("contacts-list").getAttribute("data-page");
                    if (event.target.id === "pagination-left") {
                        page = parseInt(page) - 1;
                    }
                    else {
                        page = parseInt(page) + 1;
                    }
                    let contactComponent = new ContactListComponent(contacts, appContent, page);
                    let rendered = contactComponent.render();
                },
                function (error){
                    alert("Can't load data. Please wait 1 min and try again");
                }
            );
    }
});


document.addEventListener("click", function (event) {
    if (event.target.id === "delete-contacts") {
        event.preventDefault();
        event.stopPropagation();
        let checkedElements = document.querySelectorAll("input[type='checkbox']:checked");
        if(checkedElements.length === 0) {
            alert("Sorry, by there are no checked elements!");
            return;
        }
        for(let elem of checkedElements) {
            createDELETEPromiseRequest(
                deleteContactURL + elem.parentElement.getAttribute("data-contactid"), elem.parentElement);
        }
    }
});

document.addEventListener("click", function (event) {
    if (event.target.id === "add-contact") {
        event.preventDefault();
        event.stopPropagation();
        let contactComponent = new ContactComponent(null, appContent);
        let rendered = contactComponent.renderToAdd();
    }
});

document.addEventListener("click", function (event) {
    if (event.target.id === "send-emails") {
        event.preventDefault();
        event.stopPropagation();
        let checkedElements = document.querySelectorAll("input[type='checkbox']:checked");
        if(checkedElements.length === 0) {
            alert("Sorry, by there are no checked elements!");
            return;
        }
        let emailComponent = new EmailComponent(checkedElements, appContent);
        emailComponent.render();
    }
});

ContactListComponent.prototype.render = function () {
    let template = document.getElementById("contactsListTemplate");
    this.container.innerHTML = template.innerHTML;
    let contactsContainer = document.getElementById("contacts-list");
    document.getElementById("contacts-list").setAttribute("data-page", this.pageNumber);
    for (let contact of this.contacts) {
        let item = new ContactListItemComponent(contact, contactsContainer);
        item.render();
    }
    if (this.pageNumber !== null) {
        document.getElementById("pagination-left").href = getContactsURL + (this.pageNumber - 1);
        document.getElementById("pagination-right").href = getContactsURL + (this.pageNumber + 1);
        if (this.pageNumber === 1) {
            document.getElementById("pagination-left").setAttribute("disabled", "disabled");
            document.getElementById("pagination-left").removeAttribute("href");
        }
        if (this.pageNumber === lastPage) {
            document.getElementById("pagination-right").setAttribute("disabled", "disabled");
            document.getElementById("pagination-right").removeAttribute("href");
        }
    } else {
        let left = document.getElementById("pagination-left");
        left.parentElement.removeChild(left);
        let right = document.getElementById("pagination-right");
        right.parentElement.removeChild(right);
    }
    return ContactListComponent;

};
