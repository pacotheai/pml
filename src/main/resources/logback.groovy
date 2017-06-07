import java.nio.charset.Charset

appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')
        pattern =
                '%highlight(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%highlight(%5p) ' + // Log level
                        '%highlight(---){faint} %highlight([%15.15t]){faint} ' + // Thread
                        '%highlight(%-40.40logger{39}){cyan} %highlight(:){faint} ' + // Logger
                        '%m%n%highlight' // Message
    }
}

root(INFO, ['STDOUT'])

logger 'paco.pml', DEBUG, ['STDOUT'], false
