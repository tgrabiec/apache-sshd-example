import org.apache.sshd.SshServer;
import org.apache.sshd.common.Factory;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.Environment;
import org.apache.sshd.server.ExitCallback;
import org.apache.sshd.server.PasswordAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.server.shell.ProcessShellFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * Copyright (C) 2014 Cloudius Systems, Ltd.
 *
 * This work is open source software, licensed under the terms of the
 * BSD license as described in the LICENSE file in the top-level directory.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        SshServer sshd = SshServer.setUpDefaultServer();
        sshd.setPort(22);
        sshd.setPasswordAuthenticator(new PasswordAuthenticator() {
            @Override
            public boolean authenticate(String username, String password, ServerSession session) {
                return true;
            }
        });
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider("hostkey.ser"));
        sshd.setShellFactory(new Factory<Command>() {
            @Override
            public Command create() {
                return new Command() {
                    @Override
                    public void setInputStream(InputStream inputStream) {

                    }

                    @Override
                    public void setOutputStream(OutputStream outputStream) {

                    }

                    @Override
                    public void setErrorStream(OutputStream outputStream) {

                    }

                    @Override
                    public void setExitCallback(ExitCallback exitCallback) {

                    }

                    @Override
                    public void start(Environment environment) throws IOException {
                        System.out.println("hello!");
                    }

                    @Override
                    public void destroy() {

                    }
                };
            }
        });
        sshd.start();
    }
}
