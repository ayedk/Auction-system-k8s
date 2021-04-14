const http = require('http')


setInterval(
    function(){
    const options = {
        hostname: 'localhost',
        port: 8080,
        path: '/bid/publisher',
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        }
      }
      auctionId = Math.floor(Math.random() * 10) + 1;
      bidPrice = Math.floor(Math.random() * 100) + 1;
      buyerId = Math.floor(Math.random() * 10) + 1;
      bidDate = new Date().getTime();
      
      var bid = {
          "auctionId": auctionId,
          "bidPrice": bidPrice,
          "buyerId": buyerId,
          "bidDate": bidDate
      }
      data = JSON.stringify(bid)

      const req = http.request(options, res => {
          console.log(`statusCode: ${res.statusCode}`)


          res.on('data', d => {
            console.log(d.body)
          })
        })
        
      req.on('error', error => {
          console.error(error)
      })
      req.write(data)
      req.end()
      
    }, 1000);