const instanceCheckers = (function () {
    return {
        isArray: function (obj) {
            return Object.prototype.toString.call(obj) === "[object Array]";
        },

        isBoolean: function (obj) {
            return typeof obj === "boolean";
        },

        isDate: function (obj) {
            return Object.prototype.toString.call(obj) === "[object Date]" && !isNaN(obj.getTime());
        },

        isNumber: function (obj) {
            return typeof obj === "number";
        },

        isString: function (obj) {
            return typeof obj === "string";
        },

        isFunction: function (obj) {
            return typeof obj === "function";
        },

        isUndefined: function (obj) {
            return obj === undefined;
        },

        isNull: function (obj) {
            return obj === null;
        },
        isDict: function(obj) {
            return (typeof obj==='object' &&
                obj!==null &&
                !(obj instanceof Array) &&
                !(obj instanceof Date));
    }
    };
})();
