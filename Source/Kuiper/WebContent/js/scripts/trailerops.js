        $(document).ready(function () {
            var url = "opentrailer.txt";

            // prepare the data
            var source =
            {
                datatype: "json",
                    datafields:
                    [
                      { name: 'deliverynbr', type: 'string' },
                      { name: 'trailernbr' , type: 'string' },
                      { name: 'carriernbr' , type: 'string' },
                      { name: 'truckqty'   , type: 'Int'    },
                      { name: 'door'       , type: 'string' },
                      { name: 'tstatus'    , type: 'string' },
                      { name: 'sdate'      , type: 'date'   }
                    ],
                    url: url,
                    updaterow: function (rowid, rowdata, commit)
                    {
                    // synchronize with the server - send update command
                    // call commit with parameter true if the synchronization with the server is successful 
                    // and with parameter false if the synchronization failed.
                    commit(true);
                    },
                    deleterow: function (rowid, commit)
                    {
                    // synchronize with the server - send delete command
                    // call commit with parameter true if the synchronization with the server is successful 
                    // and with parameter false if the synchronization failed.
                    commit(true);
                    }
            };

            // initialize the input fields.
            $("#deliverynbr").addClass('jqx-input');
            $("#trailernbr").addClass('jqx-input');
            $("#carriernbr").addClass('jqx-input');
            $("#truckqty").jqxNumberInput({ width: 150, height: 23,  decimalDigits: 0, spinButtons: true });
            $("#door").addClass('jqx-input');
            $("#tstatus").addClass('jqx-input');
            $("#sdate").addClass('jqx-input');

            $("#deliverynbr").width(150);
            $("#deliverynbr").height(23);

            $("#trailernbr").width(150);
            $("#trailernbr").height(23);

            $("#carriernbr").width(150);
            $("#carriernbr").height(23);

            $("#sdate").width(150);
            $("#sdate").height(23);

            $("#tstatus").width(150);
            $("#tstatus").height(23);

            $("#door").width(150);
            $("#door").height(23);
            
            if (theme.length > 0)
            {
                $("#deliverynbr").addClass('jqx-input-' + theme);
                $("#carriernbr").addClass('jqx-input-' + theme);
                $("#trailernbr").addClass('jqx-input-' + theme);
                $("#door").addClass('jqx-input-' + theme);
                $("#sdate").addClass('jqx-input-' + theme);
                $("#tstatus").addClass('jqx-input-' + theme);
            }
            
            //$("#truckqty").jqxNumberInput({ width: 150, height: 23,  decimalDigits: 0, spinButtons: true });

            //$("#price").jqxNumberInput({symbol: '$', width: 150, height: 23,  spinButtons: true });
            
            var dataAdapter = new $.jqx.dataAdapter(source,
                {
                   downloadComplete: function (data, status, xhr ) { },
                   loadComplete:     function (data              ) { },
                   loadError:        function (xhr, status, error) { }
                });

            var editrow = -1;
            // initialize jqxGrid
            $("#jqxgrid").jqxGrid(
            {
               //autowidth:true,
                width:'99%',
                //height: '100%',
                source: dataAdapter,
                showfilterrow: true,
                filterable: true,
                autoheight: true,
                pageable: true,
                sortable: true,
                columns:
                [
                  { text: 'DELIVERY#'      , datafield: 'deliverynbr'                      },
                  { text: 'TRAILER#'       , datafield: 'trailernbr'                       },
                  { text: 'CARRIER'        , datafield: 'carriernbr'                       },
                  { text: 'TRUCK QTY'      , datafield: 'truckqty'                         },
                  { text: 'DOOR'           , datafield: 'door'                             },
                  { text: 'STATUS'         , datafield: 'tstatus'                          },
                  { text: 'SCHEDULED DATE' , datafield: 'sdate',      filtertype: 'date'   }

               ]

            });

            // create context menu
            var contextMenu = $("#Menu").jqxMenu({ width: 200, height: 80, autoOpenPopup: false, mode: 'popup'});
            $("#jqxgrid").on('contextmenu', function () {
                return false;
            });
            // handle context menu clicks.
            $("#Menu").on('itemclick', function (event) {
                var args = event.args;
                var rowindex = $("#jqxgrid").jqxGrid('getselectedrowindex');
                if ($.trim($(args).text()) == "Open Trailer") {
                    editrow = rowindex;
                    var offset = $("#jqxgrid").offset();
                    $("#popupWindow").jqxWindow({ position: { x: parseInt(offset.left) + 60, y: parseInt(offset.top) + 60} });
                    // get the clicked row's data and initialize the input fields.
                    var dataRecord = $("#jqxgrid").jqxGrid('getrowdata', editrow);
                    $("#deliverynbr").val(dataRecord.deliverynbr);
                    $("#trailernbr").val(dataRecord.trailernbr);
                    $("#carriernbr").val(dataRecord.carriernbr);
                    $("#truckqty").jqxNumberInput({ decimal: dataRecord.truckqty });
                    $("#door").val(dataRecord.door);
                    $("#tstatus").val(dataRecord.tstatus);
                    $("#sdate").val(dataRecord.sdate);
                    
                    //$("#price").jqxNumberInput({ decimal: dataRecord.price });
                    // show the popup window.
                    $("#popupWindow").jqxWindow('show');
                                    }
                else if  ($.trim($(args).text()) == "Cancel Delivery")
                {
                    //success|info|warning|danger

                    var notify = $.notify('<strong>Cancelling</strong> Delivery.....', {
                        type: 'warning',
                        allow_dismiss: true,
                        showProgressbar: true
                        });

                    setTimeout(function() {
                        notify.update('message', '<strong>Cancelling POs.....</strong>');
                            }, 1000);

                    setTimeout(function() {
                        notify.update('message', '<strong>Cancelling Items.....</strong>');
                    }, 2000);

                    setTimeout(function() {
                        notify.update('message', '<strong>Notifying Traffic Manager.....</strong>');
                    }, 3000);

                    setTimeout(function() {
                        notify.update('message', '<strong>Delivery cancelled successfully.</strong>');
                    }, 4000);

                    var rowid = $("#jqxgrid").jqxGrid('getrowid', rowindex);
                    $("#jqxgrid").jqxGrid('deleterow', rowid);

                }
                else
                {
                    /*var rowid = $("#jqxgrid").jqxGrid('getrowid', rowindex);
                    $("#jqxgrid").jqxGrid('deleterow', rowid); */
                    var mywindow = window.open("/receive/delivery.html", "_self");
                }
            });
            $("#jqxgrid").on('rowclick', function (event) {
                if (event.args.rightclick) {
                    $("#jqxgrid").jqxGrid('selectrow', event.args.rowindex);
                    var scrollTop = $(window).scrollTop();
                    var scrollLeft = $(window).scrollLeft();
                    contextMenu.jqxMenu('open', parseInt(event.args.originalEvent.clientX) + 5 + scrollLeft, parseInt(event.args.originalEvent.clientY) + 5 + scrollTop);
                    return false;
                }
            });
            // initialize the popup window and buttons.
            $("#popupWindow").jqxWindow({ width: 380, resizable: false,  isModal: true, autoOpen: false, cancelButton: $("#Cancel"), modalOpacity: 0.01 });
            $("#Cancel").jqxButton({ template: "warning" });
            $("#Save").jqxButton({ template: "success" });
            // update the edited row when the user clicks the 'Save' button.
            $("#Save").click(function () {
                if (editrow >= 0) {
                    var row = {   deliverynbr: $("#deliverynbr").val()
                                , trailernbr: $("#trailernbr").val()
                                , carriernbr: $("#carriernbr").val()
                                , truckqty: parseInt($("#truckqty").jqxNumberInput('decimal'))
                                , door: $("#door").val()
                                , tstatus:"OPEN"
                                , sdate: $("#sdate").val()
                              };
                              
                    var openify = $.notify('<strong>Opening</strong> Delivery.....', {
                        type: 'success',
                        allow_dismiss: true,
                        showProgressbar: true
                        });

                    setTimeout(function() {
                        openify.update('message', '<strong>Validating Trailer Plate#.....</strong>');
                            }, 1000);

                    setTimeout(function() {
                        openify.update('message', '<strong>Validating inbound documents.....</strong>');
                    }, 2000);

                    setTimeout(function() {
                        openify.update('message', '<strong>Notifying Receiving Operations.....</strong>');
                    }, 3000);

                    setTimeout(function() {
                        openify.update('message', '<strong>Trailer Opened successfully.</strong>');
                    }, 4000);


                    var rowid = $("#jqxgrid").jqxGrid('getrowid', editrow);
                    $('#jqxgrid').jqxGrid('updaterow', rowid, row);
                    $("#popupWindow").jqxWindow('hide');
                }
            });
        });