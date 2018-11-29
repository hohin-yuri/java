function AvatarComponent(contactId, container) {
    this.contactId = contactId;
    this.container = container;
}

AvatarComponent.prototype.render = function () {
    let component = this;
    let template = document.getElementById("avatarTemplate").innerHTML;
    createGETPromiseRequest(getAvatarURL + component.contactId, (result) => result)
        .then(
            function (result){
                let rendered = _.template(template)({"url": result, "contactId": component.contactId});
                component.container.innerHTML = rendered;
            },
            function (error){
                alert("Can't load data. Please wait 1 min and try again");
            }
        );

    return AvatarComponent;
};

document.addEventListener("click", function(event) {
    if (event.target.id === "addAvatarButton") {
        event.preventDefault();
        event.stopPropagation();
        let form = document.getElementById("avatarForm");
        let file = document.getElementById("avatarInput").files[0];

        if (file && file.size / 1024 / 1024 < 5) {
            let contactId = document.getElementById("avatarForm").getAttribute("data-id");
            let xhr = new XMLHttpRequest();
            xhr.open("POST", getAvatarURL + contactId, true);
            xhr.onreadystatechange = function(){
                if (xhr.readyState !== 4) return;
                if (this.status === 200){
                    document.getElementById("avatar-image").src = this.responseText + "/?t=" + new Date().getTime();
                }
            };
            xhr.send(new FormData(document.getElementById("avatarForm")));
        }
        else {
            alert("Incorrect avatar file size!!!")
        }
        return false;
    }
});