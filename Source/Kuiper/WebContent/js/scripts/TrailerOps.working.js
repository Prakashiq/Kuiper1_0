        $(document).ready(function () {
            var url = "../../sampledata/OpenTrailer.txt";

            // prepare the data
            var source =
            {
                datatype: "json",
                    datafields:
                    [
                      { name: 'deliverynbr', type: 'string' },
                      { name: 'trailernbr' , type: 'string' },
                      { name: 'carriernbr' , type: 'string' },
                      { name: 'truckqty'   , type: 'Int' },
                      { name: 'door'       , type: 'string' },
                      { name: 'status'     , type: 'string' },
                      { name: 'sdate'      , type: 'date' }
                    ],
                    url: url
            };

            var dataAdapter = new $.jqx.dataAdapter(source,
                {
                   downloadComplete: function (data, status, xhr ) { },
                   loadComplete:     function (data              ) { },
                   loadError:        function (xhr, status, error) { }
                });


            // initialize jqxGrid
            $("#jqxgrid").jqxGrid(
            {
                width: '100%',
                //height: '100%',
                source: dataAdapter,
                showfilterrow: true,
                filterable: true,
                autoheight: true,
                pageable: true,
                sortable: true,
                columns:
                [
                  { text: 'DELIVERY#'      , datafield: 'deliverynbr', width: '16%'                      },
                  { text: 'TRAILER#'       , datafield: 'trailernbr' , width: '16%'                      },
                  { text: 'CARRIER'        , datafield: 'carriernbr' , width: '16%'                      },
                  { text: 'TRUCK QTY'      , datafield: 'truckqty'   , width: '16%'                      }, 
               // { text: 'DOOR'           , datafield: 'door'       , width: '20%'                      },
                  { text: 'STATUS'         , datafield: 'status'     , width: '16%'                      },
                  { text: 'SCHEDULED DATE' , datafield: 'sdate'      , width: '20%' , filtertype: 'date' }
               ]
            });
        });