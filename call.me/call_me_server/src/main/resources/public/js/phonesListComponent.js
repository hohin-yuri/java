function PhonesListComponent(phones, container) {
    this.container = container;
    this.phones = phones;
}

PhonesListComponent.prototype.render = function () {
    let template = document.getElementById("phonesListTemplate");
    console.log(this.container);
    document.getElementById("phones-list-container").innerHTML += template.innerHTML;
    let phonesContainer = document.getElementById("phones-list");

    let addPhoneLink = document.getElementById("add-phone");
    const addPhoneClickListener = function(event){
        event.preventDefault();
        event.stopPropagation();
    };
    const deletePhonesClickListener = function(event){
        event.preventDefault();
        event.stopPropagation();
        let checkedElements = document.querySelectorAll("input[type='checkbox']:checked");
        if(checkedElements.length === 0) {
            alert("Sorry, by there are no checked elements!");
            return;
        }
        for(let elem of checkedElements) {
            createDELETEPromiseRequest(
                deletePhoneURL + elem.parentElement.getAttribute("data-phoneid"), elem.parentElement);
        }
    };
    document.getElementById("delete-phones").addEventListener("click", deletePhonesClickListener);

    for (let phone of this.phones) {
        let item = new PhoneListItemComponent(phone, phonesContainer);
        item.render();
    }

    return PhonesListComponent;
};

document.addEventListener("click", function (event) {
    if (event.target.id === "add-phone") {
        event.preventDefault();
        event.stopPropagation();
        let modalBody = document.getElementById("body");
        let uploadComponent = new PhoneComponent(null, modalBody);
        uploadComponent.renderToAdd();
        modal.show();
    }
});

document.addEventListener("click", function (event) {
    if (event.target.id === "delete-phones") {
        event.preventDefault();
        event.stopPropagation();
        let container = document.getElementById("phones-list");
        let checkedElements = container.querySelectorAll("input[type='checkbox']:checked");
        if(checkedElements.length === 0) {
            alert("Sorry, by there are no checked elements!");
            return;
        }
        for(let elem of checkedElements) {
            createDELETEPromiseRequest(
                deletePhoneURL + elem.parentElement.getAttribute("data-phoneid"), elem.parentElement);
        }
    }
});