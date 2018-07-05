function _( selector ) {
    var self = {
        selector: selector,
        element: typeof selector !== 'object' ? document.querySelector( selector ) : document,
        html: function () {
            return self.element;
        },
        attr: function ( name, value ) {
            if ( !value ) {
                return self.element.getAttribute( name );
            }
            self.element.setAttribute( name, value );
        },
        on: function ( type, cb ) {
            self.element.addEventListener( type, cb );
        },
        ready: function ( cb ) {
            selector.addEventListener( 'DOMContentLoaded', cb, false );
        },
        addClass: function ( className ) {
            self.element.classList.add( className );
        },
        removeClass: function ( className ) {
            self.element.classList.remove( className );
        },
        toggle: function ( className ) {
            self.element.classList.toggle( className );
        },
        hasClass: function ( className ) {
            return self.element.classList.contains( className );
        }
    };

    _.ajax = function ( obj ) {
        var url = obj.url;
        var method = obj.method;
        var data = obj.data;
        var async = obj.async;
        var error = obj.error;
        var headers = obj.headers;
        var success = obj.success;

        var xhr = new XMLHttpRequest();
        xhr.open( method, url, async );
        for ( var name in headers ) {
            xhr.setRequestHeader( name, headers[name] );
        }
        xhr.onload = function () {
            if ( xhr.status === 200 ) {
                success( xhr );
            } else {
                error( xhr );
            }
        }
        xhr.send( data );
    }

    return self;
}