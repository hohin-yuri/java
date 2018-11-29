let appContent = document.getElementById("appContent");
let lastPage = 1;
createGETPromiseRequest(getPagesCountURL, (val) => val)
    .then(
        function (value) {
            lastPage = Math.ceil(value/contactsPerPage);
        }
    );
getContacts(1).then(
    function (contacts){
        let contactComponent = new ContactListComponent(contacts, appContent, 1);
        let rendered = contactComponent.render();
    },
    function (error){
        alert("Can't load data. Please wait 1 min and try again");
    }
);

let elt = document.querySelector("#modal");
let modal = new Modal(elt);