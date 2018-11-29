function Email(specs) {
    this.recipients = specs.recipients;
    this.theme = specs.theme;
    this.text = specs.text;
}

function EmailComponent(elements, container) {
    this.elements = elements;
    this.container = container;
}

let contactsToSendEmail = null;

document.addEventListener("click", function (event) {
    if (event.target.id === "email-submit") {
        event.preventDefault();
        event.stopPropagation();

        event.target.value = "wait...";
        let form = toObject(document.getElementById("email-form"));
        let recipients = [];

        for(let i of contactsToSendEmail){
            recipients.push(new Contact(i));

        }
        form["recipients"] = recipients;
        let email = new Email(form);
        createPOSTPromiseRequest(sendEmailURL, email)
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
    if (event.target.id === "cancel-send-email") {
        event.preventDefault();
        event.stopPropagation();
        contactsToSendEmail = null;
        onCloseClickDefault();
    }
});

EmailComponent.prototype.render = function () {
    contactsToSendEmail = [];
    let emails = [];
    for(let elem of this.elements){
        let contact = null;
        emails.push(elem.parentElement.getAttribute("data-email"));
        createGETPromiseRequest(getContactURL + parseInt(elem.parentElement.getAttribute("data-contactid")),
            (res) => JSON.parse(res) )
            .then(
                function (result){
                    contact = result;
                    contactsToSendEmail.push(contact);
                },
                function (error){
                    alert("Can't load data. Please wait 1 min and try again");
                }
            );
    }
    let emailTemplates = {
        "none": ``,
        "birthday wish": "Dear <name>, Happy birthday, dude!",
        "Xmath wish": "Dear <name>, marry Xmath and happy new year!",
        "Standart form": "Dear <name> ... with respect, your ...!",
        "Crazy form": "Dear <name>, I know your addresses <address>!!!"
    };
    let emailComponent = this;
    let template = document.getElementById("sendEmailTemplate").innerHTML;
    let container = this.container;
    container.innerHTML = template;
    let select = document.getElementById("templateSelect");
    for (let elem in emailTemplates){
        var opt = document.createElement('option');
        opt.value = emailTemplates[elem];
        opt.innerHTML = elem;
        select.appendChild(opt);
    }
    document.getElementById("form_email").value = emails.join(",");
};

document.onchange = (event) => {
    if (event.target.id === "templateSelect"){
        const selected = event.target.options[event.target.selectedIndex].value;
        document.getElementById("form_message").innerHTML = event.target.options[event.target.selectedIndex].value;
    }
};