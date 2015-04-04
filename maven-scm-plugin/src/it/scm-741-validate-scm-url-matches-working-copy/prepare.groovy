/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

// We rename the dotSvnDir in the IT folder to standard .svn naming so that the IT can execute.
// BUT we actually don't store that special versioning directory as-is to prevent potentially confusing 
// (to say the least) a svn checkout of the maven-scm project.
// 

println "prepare.groovy:"
File dotSvnDir = new File( basedir, 'dotSvnDir' )
assert dotSvnDir.exists()
assert dotSvnDir.isDirectory()
assert dotSvnDir.renameTo( new File( basedir, '.svn' ) )

println "svn --version"
def proc = "svn --version".execute()
proc.consumeProcessOutput()
proc.waitFor()
println "return code: ${ proc.exitValue()}"
println "stderr: ${proc.err.text}"
println "stdout: ${proc.in.text}"

println "svn upgrade $basedir"
proc = ["svn", "upgrade", "$basedir"].execute()
proc.consumeProcessOutput()
proc.waitFor()
println "return code: ${ proc.exitValue()}"
println "stderr: ${proc.err.text}"
println "stdout: ${proc.in.text}"
