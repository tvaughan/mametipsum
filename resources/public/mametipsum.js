MI = Ember.Application.create();
MI.blocks = Ember.ArrayController.create();

function is_number(string) {
    return (string.length != 0) && !isNaN(string - 0);
}

$(document).ready(function() {
    $.getJSON('/mametipsum', function(data) {
        $.each(data, function(key, value) {
            $('#title').append(new Option(value));
        });    });

    $("#generate").click(function(event) {
        event.preventDefault();

        var $nblocks = $('#nblocks').val();
        if (!is_number($nblocks)) {
            $nblocks = 0;
        }

        var $nwords = $('#nwords').val();
        if (!is_number($nwords)) {
            $nwords = 0;
        }

        var $action = '/mametipsum/' + $("#title").val();
        $action = $action + '/' + $nblocks + '/' + $nwords;

        var $jqxhr = $.get(escape($action),
                           function(data) {
                               MI.blocks.set('content', data);
                           }
                          );

        $jqxhr.error(function(request, status, error) {
            alert('FAIL');
        });
    });
});
