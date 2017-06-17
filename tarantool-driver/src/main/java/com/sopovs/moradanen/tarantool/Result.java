package com.sopovs.moradanen.tarantool;

import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;

import org.msgpack.core.MessageUnpacker;
import org.msgpack.value.ImmutableValue;

public class Result implements Closeable {

	private int counter;
	private final int size;
	private final MessageUnpacker unpacker;
	private ImmutableValue current;

	Result(MessageUnpacker unpacker) {
		try {
			this.size = unpacker.unpackArrayHeader();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		this.unpacker = unpacker;
	}

	public int getSize() {
		return size;
	}

	public boolean hasNext() {
		return counter < size;
	}

	public int getInt() {
		return current.asArrayValue().list().get(0).asIntegerValue().asInt();
	}

	public void next() {
		counter++;
		try {
			current = unpacker.unpackValue();
		} catch (IOException e) {
			throw new TarantoolException(e);
		}
	}

	@Override
	public void close() {
		while (counter < size) {
			counter++;
			try {
				// TODO seems like it may hang in case no data to read...
				unpacker.unpackValue();
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		}
	}

}