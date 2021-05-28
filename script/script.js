const http = require('http')


setInterval(
    function(){
    const options = {
        hostname: 'localhost',
        port: 8080,
        path: '/api/bids',
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        }
      }
      
      var bid = {
          "buyerId": Math.floor(Math.random() * 10) + 1,
          "bidPrice": Math.floor(Math.random() * 100) + 1,
          "auctionId": Math.floor(Math.random() * 10) + 1,
          "bidDate": new Date().getTime()
      }
      data = JSON.stringify(bid)

      const req = http.request(options, res => {
          console.log(`statusCode: ${res.statusCode}`)


          res.on('data', d => {
            process.stdout.write(d)
          })
        })
        
      req.on('error', error => {
          console.error(error)
      })
      req.write(data)
      req.end()
      
    }, 1000);