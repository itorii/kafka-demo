package com.itorii.kafka.entities.converter;

import com.itorii.kafka.entities.TemperatureRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.commons.compress.utils.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TemperatureRecordConverter {

    private static final SpecificDatumWriter<TemperatureRecord> avroEventWriter = new SpecificDatumWriter<>(com.itorii.kafka.schema.TemperatureRecord.SCHEMA$);
    private static final EncoderFactory avroEncoderFactory = EncoderFactory.get();
    private static final SpecificDatumReader<com.itorii.kafka.schema.TemperatureRecord> avroEventReader = new SpecificDatumReader<>(com.itorii.kafka.schema.TemperatureRecord.SCHEMA$);
    private static final DecoderFactory avroDecoderFactory = DecoderFactory.get();

    public static com.itorii.kafka.schema.TemperatureRecord fromBytes(final byte[] bytes) throws IOException {
        try {
            BinaryDecoder decoder = avroDecoderFactory.binaryDecoder(bytes, null);
            return avroEventReader.read(null, decoder);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new IOException("Unable to convert byte array to TemperatureRecord", ex);
        }
    }

    private static void toByteStream(final TemperatureRecord temperatureRecord, OutputStream stream) throws IOException {
        BinaryEncoder binaryEncoder = avroEncoderFactory.binaryEncoder(stream, null);
        avroEventWriter.write(temperatureRecord, binaryEncoder);
        binaryEncoder.flush();
    }

    public static byte[] toBytes(final TemperatureRecord temperatureRecord) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        toByteStream(temperatureRecord, stream);
        IOUtils.closeQuietly(stream);
        return stream.toByteArray();
    }

    public static com.itorii.kafka.schema.TemperatureRecord convert(TemperatureRecord record) throws IOException{
        return fromBytes(toBytes(record));
    }



}
