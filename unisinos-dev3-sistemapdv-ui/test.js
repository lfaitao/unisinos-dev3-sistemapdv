var fs = require('fs')
var path = require('path')

var walkSyncSimple = function (dir, filelist, limit, counter) {

    console.log(counter)

    var files = fs.readdirSync(dir)

    filelist = filelist || [];

    files.every(function (file) {
        if (fs.statSync(path.join(dir, file)).isDirectory()) {
            filelist = walkSyncSimple(path.join(dir, file), filelist, limit, counter);
            return true
        } else {
            if (counter < limit) {
                filelist.push(path.join(dir, file));
                counter++;
                return true
            } else {
                return false;
            }
        }
    });

    return filelist;
}

var files = []

walkSyncSimple('./app/src', files, 10, 0)
console.log(files)