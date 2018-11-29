
let contactsListParser = function (jsonData) {
    let data = JSON.parse(jsonData);
    return data;
};

let contactParser = function (jsonData) {
    let data = JSON.parse(jsonData);
    return new data;
};

let createGETPromiseRequest = function (url, parser) {
    return new Promise( function(resolve, reject) {
        let request = new window.XMLHttpRequest();
        request.open('GET', url);

        request.onload = function () {
            let parsed = null;
            if (this.status === 200) {
                try {
                    parsed = parser(this.responseText);
                } catch (e) {
                    if (e instanceof SyntaxError) {
                        reject(e)
                    }
                }
                resolve(parsed);
            } else {
                let error = new Error(this.statusText);
                error.code = this.status;
                reject(error);
            }
        };
        request.onerror = function () {
            reject(new Error("cant load"));
        };
        request.send();
    });
};

let createPOSTPromiseRequest = function (url, data) {
    return new Promise( function(resolve, reject) {
        let request = new window.XMLHttpRequest();
        request.open('POST', url, true);
        request.setRequestHeader("Content-Type", "application/json; charset=utf8");
        request.onreadystatechange = function () {
            let parsed = null;
            if (this.status === 200) {
                if(this.readyState === 4){
                    try {
                        parsed = this.responseText;
                    } catch (e) {
                        if (e instanceof SyntaxError) {
                            reject(e)
                        }
                    }
                    resolve(parsed);
                }
            } else {
                let error = new Error(this.statusText);
                error.code = this.status;
                reject(error);
            }
        };
        request.onerror = function () {
            reject(new Error("cant load"));
        };
        request.send(JSON.stringify(data));
    });
};



let createPUTPromiseRequest = function (url, data) {
    return new Promise( function(resolve, reject) {
        let request = new window.XMLHttpRequest();
        request.open('PUT', url, true);
        request.setRequestHeader("Content-Type", "application/json; charset=utf8");
        request.onreadystatechange = function () {
            let parsed = null;
            if (this.status === 200) {
                try {
                    parsed = this.responseText;
                } catch (e) {
                    if (e instanceof SyntaxError) {
                        reject(e)
                    }
                }
                resolve(parsed);
            } else {
                let error = new Error(this.statusText);
                error.code = this.status;
                reject(error);
            }
        };
        request.onerror = function () {
            reject(new Error("cant load"));
        };
        request.send(JSON.stringify(data));
    });
};

let createDELETEPromiseRequest = function (url, object) {
    return new Promise( function(resolve, reject) {
        let request = new window.XMLHttpRequest();
        request.open('DELETE', url, true);
        request.onreadystatechange = function () {
            let parsed = null;
            if (this.status === 200) {
                try {
                    object.parentNode.removeChild(object)
                } catch (e) {
                    if (e instanceof SyntaxError) {
                        reject(e)
                    }
                }
                resolve(parsed);
            } else {
                let error = new Error(this.statusText);
                error.code = this.status;
                reject(error);
            }
        };
        request.onerror = function () {
            reject(new Error("cant load"));
        };
        request.send();
    });
};



let getContacts = function(pageNumber) {
    return createGETPromiseRequest(getContactsURL + pageNumber, contactsListParser);
};

