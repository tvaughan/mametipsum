function is_number(string) {
    return (string.length != 0) && !isNaN(string - 0);
}

$(document).ready(function() {
    $("#mametipsum-form").submit(function(event) {
        event.preventDefault();

        var $nwords = $('#nwords').val();
        if (!is_number($nwords)) {
            $nwords = 0;
        }

        var $action = '/mametipsum/' + $("#title").val();
        $action = $action + '/' + $nwords;

        var $jqxhr = $.get(escape($action),
                           function() {
                               alert('SUCCESS');
                           }
                          );

        $jqxhr.error(function(request, status, error) {
            alert('FAIL');
        });
    });
});
